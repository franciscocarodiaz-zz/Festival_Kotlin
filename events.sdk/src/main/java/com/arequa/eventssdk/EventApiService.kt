package com.arequa.eventssdk

import com.arequa.eventssdk.serializer.AllEventDeserializer
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by FCD on 28/05/2018.
 */
class EventApiService {

    val apiClient: RetrofitEventApi

    init {
        val gson = GsonBuilder()
                .registerTypeAdapter(AllEvent::class.java, AllEventDeserializer)
                .create()
        val apiClientConfig = Retrofit.Builder()
                .baseUrl(Routes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        apiClient = apiClientConfig.create(RetrofitEventApi::class.java)
    }
}