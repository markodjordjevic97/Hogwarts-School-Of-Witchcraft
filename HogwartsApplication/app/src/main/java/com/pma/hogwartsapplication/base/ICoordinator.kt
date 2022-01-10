package com.pma.hogwartsapplication.base

interface ICoordinator {
    fun showWizardListFragment(houseName: String)
    fun showWizardDetailsFragment(wizardName: String, houseName: String)
}