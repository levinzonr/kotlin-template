package dk.eboks.app.presentation.ui.components.channels.content

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import dk.eboks.app.presentation.base.BaseWebFragment
import javax.inject.Inject

/**
 * Created by bison on 09-02-2018.
 */
class ChannelContentComponentFragment : BaseWebFragment(), ChannelContentComponentContract.View {

    @Inject
    lateinit var presenter : ChannelContentComponentContract.Presenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        presenter.onViewCreated(this, lifecycle)
    }

    override fun setupTranslations() {

    }

    override fun onOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return false
    }

    override fun onLoadFinished(view: WebView?, url: String?) {

    }
}