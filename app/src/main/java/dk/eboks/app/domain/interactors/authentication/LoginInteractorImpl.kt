package dk.eboks.app.domain.interactors.authentication

import dk.eboks.app.domain.managers.*
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.domain.models.local.ViewError
import dk.eboks.app.network.Api
import dk.eboks.app.util.exceptionToViewError
import dk.eboks.app.util.guard
import dk.nodes.arch.domain.executor.Executor
import dk.nodes.arch.domain.interactor.BaseInteractor
import timber.log.Timber

/**
 * Created by bison on 24-06-2017.
 */
class LoginInteractorImpl(
        executor: Executor, val api: Api,
        val appStateManager: AppStateManager,
        val userManager: UserManager,
        val userSettingsManager: UserSettingsManager,
        val authClient: AuthClient,
        val cacheManager: CacheManager) : BaseInteractor(executor), LoginInteractor {
    override var output: LoginInteractor.Output? = null
    override var input: LoginInteractor.Input? = null

    override fun execute() {
        try {
            input?.let { args ->
                try {
                    val useLongToken = userSettingsManager.get(args.loginState.selectedUser?.id ?: -1).stayLoggedIn

                    val token = authClient.login(
                            username = args.loginState.userName ?: "",
                            password = args.loginState.userPassWord ?: "",
                            activationCode = args.loginState.activationCode,
                            longClient = useLongToken
                    )

                    token?.let { t ->
                        appStateManager.state?.loginState?.token = t

                        val userResult = api.getUserProfile().execute()
                        userResult?.body()?.let { user ->
                            // update the states
                            Timber.i("Saving user $user")
                            val newUser = userManager.put(user)
                            val newSettings = userSettingsManager.get(newUser.id)

                            appStateManager.state?.loginState?.userLoginProviderId?.let {
                                newSettings.lastLoginProviderId = it
                            }
                            args.loginState.activationCode?.let {
                                newSettings.activationCode = it
                            }

                            appStateManager.state?.loginState?.lastUser?.let { lastUser ->
                                if (lastUser.id != newUser.id) {
                                    Timber.e("Different user id detected on login, clearing caches")
                                    cacheManager.clearStores()
                                }
                            }

                            userSettingsManager.put(newSettings)
                            appStateManager.state?.loginState?.lastUser = newUser
                            appStateManager.state?.currentUser = newUser
                            appStateManager.state?.currentSettings = newSettings
                        }
                        appStateManager.save()

                        runOnUIThread {
                            output?.onLoginSuccess(t)
                        }
                    }.guard {
                        runOnUIThread {
                            output?.onLoginError(ViewError(title = Translation.error.genericTitle, message = Translation.error.genericMessage, shouldCloseView = true)) // TODO better error
                        }
                    }
                }
                catch (e : AuthException)
                {
                    e.printStackTrace()
                    if(e.httpCode == 400)
                    {
                        runOnUIThread {
                            output?.onLoginActivationCodeRequired()
                        }
                    }
                    else
                    {
                        runOnUIThread {
                            output?.onLoginDenied(ViewError(title = Translation.error.genericTitle, message = Translation.logoncredentials.invalidPassword, shouldCloseView = true)) // TODO better error
                        }
                    }
                }

            }
        } catch (t: Throwable) {
            runOnUIThread {
                output?.onLoginError(exceptionToViewError(t))
            }
        }
    }
}