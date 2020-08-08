package com.erkhgns.simpleapplication.network

import com.haroldadmin.cnradapter.NetworkResponse

class WebServiceRepository(private val retrofitClient: RetrofitClient = RetrofitClient()) {

    suspend fun getUsers():NetworkResponse<List<User>,ErrorResponse> =
        retrofitClient
            .getApiClient("https://jsonplaceholder.typicode.com/")
            .getUsers()

}