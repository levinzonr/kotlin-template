package dk.eboks.app.presentation.base

interface ViewController {

    var isVerificationSucceeded: Boolean

    var refreshChannelComponent: Boolean

    fun refreshAllOnResume()
}