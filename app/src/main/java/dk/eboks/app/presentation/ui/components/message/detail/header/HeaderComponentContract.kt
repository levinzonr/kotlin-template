package dk.eboks.app.presentation.ui.components.message.detail.header

import dk.eboks.app.domain.models.message.Message
import dk.nodes.arch.presentation.base.BasePresenter
import dk.nodes.arch.presentation.base.BaseView

/**
 * Created by bison on 07-11-2017.
 */
interface HeaderComponentContract {
    interface View : BaseView {
        fun updateView(message : Message)
    }

    interface Presenter : BasePresenter<HeaderComponentContract.View> {
    }
}