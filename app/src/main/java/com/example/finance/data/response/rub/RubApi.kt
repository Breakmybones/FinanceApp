package com.example.finance.data.response.rub

import retrofit2.http.GET

interface RubApi {

    @GET("/")
    suspend fun getRub(): RubResponse
}