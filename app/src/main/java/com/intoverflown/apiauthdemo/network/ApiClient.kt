package com.intoverflown.apiauthdemo.network

object ApiClient {

    // RECENT
//    private val retrofit by lazy {
//        Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//    }
//
//    val api: ApiInterface by lazy {
//        retrofit.create(ApiInterface::class.java)
//    }

    // OLD
//    fun <Api> buildApi(
//        api: Class<Api>
//    ) : Api {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(OkHttpClient.Builder().also { client ->
//                if (BuildConfig.DEBUG) {
//                    val logging = HttpLoggingInterceptor()
//                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//                    client.addInterceptor(logging)
//                }
//            }.build())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(api)
//    }

}