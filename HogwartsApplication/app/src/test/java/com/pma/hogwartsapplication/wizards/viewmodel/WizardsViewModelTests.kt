package com.pma.hogwartsapplication.wizards.viewmodel

import androidx.lifecycle.Observer
import com.appcrafters.brewery.base.InstantExecutorTest
import com.appcrafters.brewery.base.TestCoroutineContextProvider
import com.pma.hogwartsapplication.base.data.IHogwartsDataSource
import com.pma.hogwartsapplication.base.functional.ResponseState.*
import com.pma.hogwartsapplication.base.model.Wizard
import com.pma.hogwartsapplication.view.wizards.WizardsViewState
import com.pma.hogwartsapplication.view.wizards.WizardsViewState.*
import com.pma.hogwartsapplication.viewmodel.WizardsViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks

class WizardsViewModelTests : InstantExecutorTest() {


    @Mock
    lateinit var dataSource: IHogwartsDataSource
    @Mock
    lateinit var stateObserver: Observer<WizardsViewState>  // da vidimo state koji je, posto metoda ne vraca nista

    lateinit var viewModel: WizardsViewModel

    @Before
    fun setUp() {
        openMocks(this)
        viewModel = WizardsViewModel(dataSource, TestCoroutineContextProvider())
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `test getWizards, has result, state changed to Processing - DataReceived`() = runBlocking {

        val expectedResult: List<Wizard> = listOf()

        `when`(dataSource.getWizardsByHogwartsHouse("slytherin")).thenReturn(Success(expectedResult))

        viewModel.getWizardsByHogwartsHouse("slytherin")

        verify(stateObserver).onChanged(Processing) // prvo stanje loading...
        verify(stateObserver).onChanged(DataReceived(expectedResult)) // drugo stanje ReceivedData
    }

    @Test
    fun `test getWizards, has error, state changed to Processing - ErrorReceived`() = runBlocking {

        val expectedError = Exception("test")

        `when`(dataSource.getWizardsByHogwartsHouse(anyString())).thenReturn(Error(expectedError))

        viewModel.getWizardsByHogwartsHouse(anyString())

        verify(stateObserver).onChanged(Processing)
        verify(stateObserver).onChanged(ErrorReceived(expectedError.toString()))
    }
}