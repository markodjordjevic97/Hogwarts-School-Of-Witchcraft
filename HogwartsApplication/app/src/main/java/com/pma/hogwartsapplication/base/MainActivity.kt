package com.pma.hogwartsapplication.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pma.hogwartsapplication.R
import com.pma.hogwartsapplication.view.houses.HousesFragment
import com.pma.hogwartsapplication.view.wizarddetails.WizardDetails
import com.pma.hogwartsapplication.view.wizards.WizardsFragment

class MainActivity : AppCompatActivity(), ICoordinator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_HogwartsApplication)
        setContentView(R.layout.activity_main)
        showFragment(HousesFragment(), true)
    }

    private fun showFragment(fragment: Fragment, addAsRoot: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        if (!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showWizardListFragment(houseName: String) {
        showFragment(WizardsFragment.newInstance(houseName))
    }

    override fun showWizardDetailsFragment(wizardName: String, houseName: String) {
        showFragment(WizardDetails.newInstance(wizardName, houseName))
    }
}