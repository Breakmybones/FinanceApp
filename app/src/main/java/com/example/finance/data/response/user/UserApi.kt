package com.example.finance.data.response.user

import com.example.finance.data.response.user.response.TransferData
import com.example.finance.data.response.user.response.UserAccountResponse
import com.example.finance.data.response.user.response.UserResponse
import com.example.finance.data.response.user.response.UserTransactionsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("/employee/{id}")
    suspend fun getUser(
        @Path("id") id: String?,
    ): UserResponse

    @GET("/account/{id}")
    suspend fun getUserAccount(
        @Path("id") id: String?
    ): UserAccountResponse

    @GET("/account/{email}/{password}")
    suspend fun getUserId(
        @Path("email") email: String?,
        @Path("password") password: String?
    ): String

    @GET("/account/{id}/transactions")
    suspend fun getUserTransactions(
        @Path("id") id: String?
    ): UserTransactionsResponse

    @POST("/account/transfer")
    suspend fun transfer(@Body transferData: TransferData)
}