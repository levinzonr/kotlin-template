package dk.nodes.template.presentation.ui.main

import dk.nodes.nstack.kotlin.models.AppUpdate
import dk.nodes.nstack.kotlin.models.Message
import dk.nodes.nstack.kotlin.models.RateReminder
import dk.nodes.template.presentation.util.SingleEvent

data class MainActivityViewState(
    val errorMessage: SingleEvent<String>? = null,
    val isLoading: Boolean = false,
    val nstackMessage: Message? = null,
    val nstackRateReminder: RateReminder? = null,
    val nstackUpdate: AppUpdate? = null
)