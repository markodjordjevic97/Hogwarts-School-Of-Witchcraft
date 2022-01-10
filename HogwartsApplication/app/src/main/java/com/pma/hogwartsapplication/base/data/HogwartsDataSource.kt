package com.pma.hogwartsapplication.base.data

import com.pma.hogwartsapplication.base.functional.ResponseState
import com.pma.hogwartsapplication.base.model.Wizard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

interface IHogwartsDataSource {
    suspend fun getWizardsByHogwartsHouse(house: String): ResponseState<List<Wizard>>
}
class HogwartsDataSource (private val api: HogwartsAPIService) : IHogwartsDataSource {

    override suspend fun getWizardsByHogwartsHouse(house: String): ResponseState<List<Wizard>>
                                            = handleCall(api.getWizardsByHogwartsHouse(house))

    private suspend fun <T> handleCall(call: Call<T>): ResponseState<T> {

        return withContext(Dispatchers.IO) {
            val response = call.execute()
            if (response.isSuccessful) {
                ResponseState.Success(response.body()!!)
            } else {
                ResponseState.Error(Exception(response.message()))
            }
        }
    }
}