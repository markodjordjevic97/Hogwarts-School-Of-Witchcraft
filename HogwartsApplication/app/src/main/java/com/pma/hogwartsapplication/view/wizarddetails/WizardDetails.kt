package com.pma.hogwartsapplication.view.wizarddetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pma.hogwartsapplication.R
import com.pma.hogwartsapplication.base.data.APIService
import com.pma.hogwartsapplication.base.data.HogwartsDataSource
import com.pma.hogwartsapplication.base.functional.CoroutineContextProvider
import com.pma.hogwartsapplication.base.functional.ViewModelFactoryUtil
import com.pma.hogwartsapplication.base.model.Wizard
import com.pma.hogwartsapplication.view.wizards.WizardsViewState.*
import com.pma.hogwartsapplication.viewmodel.WizardDetailsViewModel
import kotlinx.android.synthetic.main.fragment_wizard_details.*
import java.util.ArrayList

class WizardDetails : Fragment() {

    private var wizardName: String? = null
    private var houseName: String? = null
    private lateinit var viewModel : WizardDetailsViewModel

    companion object {

        private const val WIZARD_INITIAL = "WIZARD_INITIAL"

        fun newInstance(characterName: String, houseName: String): WizardDetails {
            return WizardDetails().apply {
                arguments = Bundle().apply { putStringArrayList(WIZARD_INITIAL, arrayListOf(characterName, houseName)) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arrayOfArguments: ArrayList<String>? = arguments?.getStringArrayList(WIZARD_INITIAL)
        wizardName = arrayOfArguments?.get(0).toString()
        houseName = arrayOfArguments?.get(1).toString()

        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            WizardDetailsViewModel(HogwartsDataSource(APIService.hogwartsApiService),
                CoroutineContextProvider()
            )
        })[WizardDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wizard_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->

            wizardDetailsProgressBar.isVisible = state is Processing

            when (state) {
                is DataReceived -> setUpView(state.wizards)
                is ErrorReceived -> showError(state.message)
            }
        })

        viewModel.getWizardsByHogwartsHouse(houseName!!)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpView(characters: List<Wizard>) {
        val wizardFiltered: List<Wizard> = characters.filter { it.name == wizardName }
        val wizard: Wizard = wizardFiltered[0]

        val view = WizardDetailView(requireContext())
        view.bind(wizard)
        wizardDetailLayout.addView(view)
    }
}