package com.pma.hogwartsapplication.base.data

import com.pma.hogwartsapplication.base.model.Wizard
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HogwartsAPIService {
    @GET("characters/house/{house}")
    fun getWizardsByHogwartsHouse(@Path("house") house: String): Call<List<Wizard>>
}