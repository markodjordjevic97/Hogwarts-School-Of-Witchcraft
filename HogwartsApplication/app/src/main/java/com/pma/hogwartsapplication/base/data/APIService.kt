package com.pma.hogwartsapplication.base.data

object APIService {
    val hogwartsApiService: HogwartsAPIService = RetrofitBuilder.retrofit.create(HogwartsAPIService::class.java)
}