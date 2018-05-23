package dk.eboks.app.network.repositories

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dk.eboks.app.domain.models.folder.Folder
import dk.eboks.app.domain.repositories.FoldersRepository
import dk.eboks.app.network.Api
import dk.eboks.app.storage.base.CacheStore
import timber.log.Timber

typealias FolderListStore = CacheStore<Int, List<Folder>>

/**
 * Created by bison on 01/02/18.
 */
class FoldersRestRepository(val context: Context, val api: Api, val gson: Gson) : FoldersRepository {

    val folderStore: FolderListStore by lazy {
        FolderListStore(context, gson, "folder_list_store.json", object : TypeToken<MutableMap<Int, List<Folder>>>() {}.type, { key ->
            val response = api.getFolders().execute()
            var result : List<Folder>? = null
            response?.let {
                if(it.isSuccessful)
                    result = it.body()
            }
            result
        })
    }

    override fun getFolders(cached: Boolean): List<Folder> {
        val res = if(cached) folderStore.get(0) else folderStore.fetch(0)
        if(res != null) {
            // TODO remove hack at some point
            return res[0].folders
        }
        else
            return ArrayList()
    }
}