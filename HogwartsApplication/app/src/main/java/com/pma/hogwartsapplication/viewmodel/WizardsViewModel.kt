package com.pma.hogwartsapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pma.hogwartsapplication.base.data.IHogwartsDataSource
import com.pma.hogwartsapplication.base.functional.ICoroutineContextProvider
import com.pma.hogwartsapplication.base.functional.ResponseState
import com.pma.hogwartsapplication.view.wizards.WizardsViewState
import com.pma.hogwartsapplication.view.wizards.WizardsViewState.*
import kotlinx.coroutines.launch

class WizardsViewModel (
    private val dataSource: IHogwartsDataSource,
    private val coroutineContextProvider: ICoroutineContextProvider
) : ViewModel() {

    private val _state = MutableLiveData<WizardsViewState>()
    val state: LiveData<WizardsViewState>
        get() = _state

    fun getWizardsByHogwartsHouse(house: String) {
        viewModelScope.launch(coroutineContextProvider.io) {
            _state.postValue(Processing)

            _state.postValue(
                when (val result = dataSource.getWizardsByHogwartsHouse(house)) {
                    is ResponseState.Success -> DataReceived(result.data)
                    is ResponseState.Error -> ErrorReceived(result.exception.toString())
                }
            )
        }
    }
}