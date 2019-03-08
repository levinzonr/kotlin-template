package dk.eboks.app.senders.presentation.ui.screens.registrations

import dk.eboks.app.domain.senders.interactors.register.GetRegistrationsInteractor
import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.domain.models.sender.Registrations
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by Christian on 3/28/2018.
 * @author Christian
 * @since 3/28/2018.
 */
internal class RegistrationsPresenter @Inject constructor(registrationsInteractor: GetRegistrationsInteractor) :
    BasePresenterImpl<RegistrationsContract.View>(),
    RegistrationsContract.Presenter,
    GetRegistrationsInteractor.Output {

    init {
        registrationsInteractor.output = this
        registrationsInteractor.run()
    }

    override fun onRegistrationsLoaded(registrations: Registrations) {
        view { showRegistrations(registrations) }
    }

    override fun onError(error: ViewError) {
        view { showErrorDialog(error) }
    }
}