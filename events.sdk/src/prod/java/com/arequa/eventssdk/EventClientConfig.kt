package com.arequa.eventssdk

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Created by FCD on 30/05/2018.
 */
class EventClientConfig: EventApiConfig {
    override fun setupConfig(builder: Retrofit.Builder) {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .build()
        builder.client(okHttpClient)
    }
}