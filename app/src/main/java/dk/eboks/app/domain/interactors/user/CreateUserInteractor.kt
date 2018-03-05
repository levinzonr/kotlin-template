package dk.eboks.app.domain.interactors.user

import dk.eboks.app.domain.models.internal.User
import dk.nodes.arch.domain.interactor.Interactor

/**
 * Created by bison on 24-06-2017.
 */
interface CreateUserInteractor : Interactor
{
    var input : Input?
    var output : Output?

    data class Input(val user: User)

    interface Output {
        fun onCreateUser(user : User)
        fun onCreateUserError(msg : String)
    }
}