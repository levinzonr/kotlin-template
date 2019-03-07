package dk.eboks.app.keychain.presentation.components

import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.keychain.interactors.authentication.ResetPasswordInteractor
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
internal class ForgotPasswordComponentPresenter @Inject constructor(
    private val resetPasswordInteractor: ResetPasswordInteractor
) :
    ForgotPasswordComponentContract.Presenter,
    BasePresenterImpl<ForgotPasswordComponentContract.View>(),
    ResetPasswordInteractor.Output {

    init {
        resetPasswordInteractor.output = this
    }

    override fun resetPassword(email: String) {
        resetPasswordInteractor.input = ResetPasswordInteractor.Input(email)
        resetPasswordInteractor.run()
    }

    override fun onSuccess() {
        view { showSuccess() }
    }

    override fun onError(error: ViewError) {
        // view { showError(error) }
    }
}