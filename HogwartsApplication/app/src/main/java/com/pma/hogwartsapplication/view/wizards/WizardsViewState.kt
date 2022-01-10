package com.pma.hogwartsapplication.view.wizards

import com.pma.hogwartsapplication.base.model.Wizard

sealed class WizardsViewState {
    object Processing: WizardsViewState()
    data class DataReceived(val wizards: List<Wizard>) : WizardsViewState()
    data class ErrorReceived(val message: String) : WizardsViewState()
}