package com.intoverflown.apiauthdemo.login.models

//class ViewModel(private val authRepository: AuthRepository) : ViewModel() {
//
//    private val myResponse : MutableLiveData<ApiResponseHandler<LoginResponse>> = MutableLiveData()
//    val loginResponse : LiveData<ApiResponseHandler<LoginResponse>> get() = myResponse
//
//    fun login(username : String, password : String) {
//        viewModelScope.launch {
//            myResponse.value= authRepository.login(username, password)
//        }
//    }
//}

// 2nd Test

//class ViewModel(private val repository : Repository) : ViewModel() {
//
//
//    val myResponse : MutableLiveData<Call<LoginResponse>> = MutableLiveData()
//
//    fun login(username: String, password: String) {
//        viewModelScope.launch {
//            val response = repository.login(username, password)
//            myResponse.value = response
//        }
//    }
//}