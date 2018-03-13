package dk.eboks.app.domain.interactors.channel

import dk.eboks.app.domain.models.channel.Channel
import dk.nodes.arch.domain.interactor.Interactor

/**
 * Created by bison on 01/02/18.
 */
interface GetChannelInteractor : Interactor {
    var output : Output?
    var input : Input?

    data class Input(val id : Long)

    interface Output {
        fun onGetChannel(channel : Channel)
        fun onGetChannelError(msg : String)
    }
}