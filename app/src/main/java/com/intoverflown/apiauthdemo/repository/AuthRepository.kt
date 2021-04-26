package com.intoverflown.apiauthdemo.repository

import com.intoverflown.apiauthdemo.network.ApiInterface

class AuthRepository(private val api : ApiInterface) : BaseRepository() {

    // login
    suspend fun login(username: String, password : String) = safeApiCall {
        api.login(username, password)
    }
}