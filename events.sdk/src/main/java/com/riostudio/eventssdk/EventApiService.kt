package com.riostudio.eventssdk

import com.riostudio.eventssdk.serializer.AllEventDeserializer
import com.riostudio.eventssdk.serializer.ListAllEventDeserializer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by FCD on 28/05/2018.
 */
class EventApiService(val apiConfig: EventApiConfig = EventClientConfig()) {

    val apiClient: RetrofitEventApi

    init {
        val tokenType = object : TypeToken<ArrayList<AllEvent>>() {}.type

        val gson = GsonBuilder()
                .registerTypeAdapter(AllEvent::class.java, AllEventDeserializer())
                .registerTypeAdapter(tokenType, ListAllEventDeserializer())
                .create()

        val apiClientConfig = Retrofit.Builder()
                .baseUrl(Routes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        apiConfig.setupConfig(apiClientConfig)

        apiClient = apiClientConfig.build().create(RetrofitEventApi::class.java)
    }
}