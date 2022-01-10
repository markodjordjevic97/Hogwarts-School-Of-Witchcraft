package com.pma.hogwartsapplication.view.wizards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.pma.hogwartsapplication.R
import com.pma.hogwartsapplication.base.ICoordinator
import com.pma.hogwartsapplication.base.data.APIService
import com.pma.hogwartsapplication.base.data.HogwartsDataSource
import com.pma.hogwartsapplication.base.functional.CoroutineContextProvider
import com.pma.hogwartsapplication.base.functional.ViewModelFactoryUtil
import com.pma.hogwartsapplication.base.model.Wizard
import com.pma.hogwartsapplication.viewmodel.WizardsViewModel
import com.pma.hogwartsapplication.view.wizards.WizardsViewState.*
import com.pma.hogwartsapplication.view.wizards.recycler.WizardRVAdapter
import kotlinx.android.synthetic.main.fragment_wizards.*


class WizardsFragment : Fragment() {

    private lateinit var viewModel: WizardsViewModel
    private var houseName: String? = null

    companion object {

        private const val WIZARD_HOUSE = "WIZARD_HOUSE"

        fun newInstance(houseName: String): WizardsFragment {
            return WizardsFragment().apply {
                arguments = Bundle().apply { putString(WIZARD_HOUSE, houseName) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            WizardsViewModel(
                HogwartsDataSource(APIService.hogwartsApiService),
                CoroutineContextProvider()
            )
        })[WizardsViewModel::class.java]

        houseName = arguments?.getString(WIZARD_HOUSE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wizards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFromViewModel()
        viewModel.getWizardsByHogwartsHouse(houseName!!)
    }

    private fun bindFromViewModel() {

        viewModel.state.observe(viewLifecycleOwner) { state ->

            wizardsProgressBar.isVisible = state is Processing

            when (state) {
                is DataReceived -> { setUpRecyclerView(state.wizards) }
                is ErrorReceived -> showError(state.message)
            }
        }
    }

    private fun setUpRecyclerView(wizards: List<Wizard>) {
        wizardsRV.adapter = WizardRVAdapter(wizards) { characterName ->
            (activity as ICoordinator).showWizardDetailsFragment(characterName, houseName!!)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}