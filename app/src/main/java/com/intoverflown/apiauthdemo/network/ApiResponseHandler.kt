package com.intoverflown.apiauthdemo.network

//sealed class ApiResponseHandler<out T> {
//    data class Success<out T>(val value: T) : ApiResponseHandler<T>()
//
//    data class Failure(
//        val isNetworkError : Boolean,
//        val errorCode : Int?,
//        val errorBody: ResponseBody?
//    ) : ApiResponseHandler<Nothing>()
//}