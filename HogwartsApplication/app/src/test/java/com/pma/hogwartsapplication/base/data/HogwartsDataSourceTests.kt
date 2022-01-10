package com.pma.hogwartsapplication.base.data

import com.pma.hogwartsapplication.base.functional.ResponseState.*
import com.pma.hogwartsapplication.base.model.Wizard
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations.openMocks
import retrofit2.Call
import retrofit2.Response

class HogwartsDataSourceTests {

    @Mock
    lateinit var apiService: HogwartsAPIService
    @Mock
    lateinit var getWizardsCall: Call<List<Wizard>> // retrofit class

    lateinit var dataSource: HogwartsDataSource

    @Before
    fun setUp() {
        openMocks(this)
        dataSource = HogwartsDataSource(apiService)
    }

    @Test
    fun `test getWizards, has response, Success returned`() = runBlocking {
        // runBlockig = blokira glavni thread, samo iz testova ga pozivati
        val expectedCharacters: List<Wizard> = listOf()
        val expectedResult = Success(expectedCharacters)

        `when`(apiService.getWizardsByHogwartsHouse("slytherin")).thenReturn(getWizardsCall)
        `when`(getWizardsCall.execute()).thenReturn(Response.success(expectedCharacters))

        val result = dataSource.getWizardsByHogwartsHouse("slytherin")

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test getWizards, has error, Error returned`() = runBlocking {

        val expectedResponseBody = ResponseBody.create(
            MediaType.parse("application/json"), ""
        )

        `when`(apiService.getWizardsByHogwartsHouse(anyString())).thenReturn(getWizardsCall)
        `when`(getWizardsCall.execute()).thenReturn(Response.error(400, expectedResponseBody))

        val result = dataSource.getWizardsByHogwartsHouse(anyString())

        assertTrue(result is Error)
    }
}