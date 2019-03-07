package dk.eboks.app.senders.presentation.ui.screens.browse

import dk.eboks.app.domain.managers.AppStateManager
import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.domain.models.sender.Sender
import dk.eboks.app.mail.domain.interactors.senders.GetSendersInteractor
import dk.nodes.arch.presentation.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by bison on 20-05-2017.
 */
internal class BrowseCategoryPresenter @Inject constructor(
    private val appStateManager: AppStateManager,
    private val getSendersInteractor: GetSendersInteractor
) :
    BrowseCategoryContract.Presenter,
    BasePresenterImpl<BrowseCategoryContract.View>(),
    GetSendersInteractor.Output {

    init {
        getSendersInteractor.output = this
    }

    override fun loadSenders(senderId: Long) {
        view { showProgress(true) }
        getSendersInteractor.input = GetSendersInteractor.Input(
            false,
            "",
            appStateManager.state?.impersoniateUser?.userId,
            senderId
        )
        getSendersInteractor.run()
    }

    override fun searchSenders(searchText: String) {
        view { showProgress(true) }
        if (searchText.isNotBlank()) {
            getSendersInteractor.input = GetSendersInteractor.Input(
                false,
                searchText,
                appStateManager.state?.impersoniateUser?.userId
            )
            getSendersInteractor.run()
        } else {
            onGetSenders(emptyList()) // empty result
        }
    }

    override fun onGetSenders(senders: List<Sender>) {
        view {
            showProgress(false)
            showSenders(senders)
        }
    }

    override fun onGetSendersError(error: ViewError) {
        view {
            showProgress(false)
            showSenders(emptyList()) // empty result
            showErrorDialog(error)
        }
    }
}