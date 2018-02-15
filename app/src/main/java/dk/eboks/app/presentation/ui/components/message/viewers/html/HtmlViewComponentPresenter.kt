package dk.eboks.app.presentation.ui.components.message.viewers.html

import dk.eboks.app.domain.managers.AppStateManager
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
class HtmlViewComponentPresenter @Inject constructor(val appState: AppStateManager) : HtmlViewComponentContract.Presenter, BasePresenterImpl<HtmlViewComponentContract.View>() {

    init {
    }

}