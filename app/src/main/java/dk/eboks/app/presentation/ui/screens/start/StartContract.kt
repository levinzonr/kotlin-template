package dk.eboks.app.presentation.ui.screens.start

import dk.nodes.arch.presentation.base.BasePresenter
import dk.nodes.arch.presentation.base.BaseView

/**
 * Created by bison on 07-11-2017.
 */
interface StartContract {
    interface View : BaseView {
        fun performVersionControl()
        fun startMain()
        fun showWelcomeComponent()
        fun showLoginComponent()
        fun showError(msg : String)
    }

    interface Presenter : BasePresenter<View> {
        fun proceed()
    }
}