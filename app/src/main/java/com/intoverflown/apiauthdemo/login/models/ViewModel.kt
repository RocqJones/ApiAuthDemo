package com.intoverflown.apiauthdemo.login.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intoverflown.apiauthdemo.data.LoginResponse
import com.intoverflown.apiauthdemo.network.ApiResponseHandler
import com.intoverflown.apiauthdemo.repository.AuthRepository
import kotlinx.coroutines.launch

class ViewModel(private val authRepository: AuthRepository) : ViewModel() {

    val myResponse : MutableLiveData<ApiResponseHandler<LoginResponse>> = MutableLiveData()
    val loginResponse : LiveData<ApiResponseHandler<LoginResponse>> get() = myResponse

    fun login(username : String, password : String) {
        viewModelScope.launch {
            myResponse.value= authRepository.login(username, password)
        }
    }
}