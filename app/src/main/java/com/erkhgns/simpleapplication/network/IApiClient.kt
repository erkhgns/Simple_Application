package com.erkhgns.simpleapplication.network

import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.POST


interface IApiClient {

    @GET("users")
    suspend fun getUsers(): NetworkResponse< List<User>, ErrorResponse>

}