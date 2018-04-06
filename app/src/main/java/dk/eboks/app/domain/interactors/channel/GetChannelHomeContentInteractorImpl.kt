package dk.eboks.app.domain.interactors.channel

import dk.eboks.app.domain.models.home.HomeContent
import dk.eboks.app.domain.repositories.ChannelsRepository
import dk.eboks.app.util.exceptionToViewError
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor
import kotlinx.coroutines.experimental.*
import timber.log.Timber

/**
 * Created by bison on 01/02/18.
 */
class GetChannelHomeContentInteractorImpl(executor: Executor, val channelsRepository: ChannelsRepository) : BaseInteractor(executor), GetChannelHomeContentInteractor {
    override var output: GetChannelHomeContentInteractor.Output? = null
    override var input: GetChannelHomeContentInteractor.Input? = null

    override fun execute() {
        try {
            val pinnedChannels = channelsRepository.getPinnedChannels()
            runOnUIThread {
                output?.onGetPinnedChannelList(pinnedChannels)
            }
            if(pinnedChannels.isNotEmpty())
            {
                Timber.e("channel home content loading started for ${pinnedChannels.size} channels")
                /*
                val controls : MutableList<Deferred<HomeContent>> = ArrayList()
                for (channel in pinnedChannels) {
                    val d = async { channelsRepository.getChannelHomeContent(channel.id.toLong()) }
                    controls.add(d)
                }
                //val result : MutableList<Control> = ArrayList()

                runBlocking {
                    launch(CommonPool) {
                        controls.forEach {
                            it.await()
                            val content = it.getCompleted()
                            //Timber.e("Got HomeContent $content")
                            /*
                            runOnUIThread {
                                output?.onGetChannelHomeContent(it.getCompleted())
                            }
                            */
                        }
                    }
                }

                Timber.e("channel home content loading completed, loaded ${controls.size} controls")
                */
                val c = channelsRepository.getChannelHomeContent(1)
                Timber.e("Got homecontent $c")

                /*
                for(channel in pinnedChannels)
                {
                    val c = channelsRepository.getChannelHomeContent(channel.id.toLong())
                    Timber.e("Got homecontent $c")
                }
                */
            }
            else    // there are no pinned channels
            {

            }
        } catch (t: Throwable) {
            runOnUIThread {
                t.printStackTrace()
                val ve = exceptionToViewError(t)
                output?.onGetChannelHomeContentError(ve)
            }
        }
    }

}