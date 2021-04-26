package com.intoverflown.apiauthdemo.network

import com.intoverflown.apiauthdemo.data.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface ApiInterface {

    @POST("Service/POS/API/v1/Connect/Login")
    suspend fun login(username: String, password: String): Response<LoginResponse>
}