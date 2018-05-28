package com.arequa.eventssdk

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by FCD on 28/05/2018.
 */
class EventApiService {

    val apiClient: RetrofitEventApi

    init {
        val apiClientConfig = Retrofit.Builder()
                .baseUrl(Routes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiClient = apiClientConfig.create(RetrofitEventApi::class.java)
    }
}