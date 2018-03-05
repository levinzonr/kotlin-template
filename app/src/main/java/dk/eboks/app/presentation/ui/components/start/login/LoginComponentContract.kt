package dk.eboks.app.presentation.ui.components.start.login

import dk.nodes.arch.presentation.base.BasePresenter
import dk.nodes.arch.presentation.base.BaseView

/**
 * Created by bison on 07-11-2017.
 */
interface LoginComponentContract {
    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {
        fun createUser(email : String)
    }
}