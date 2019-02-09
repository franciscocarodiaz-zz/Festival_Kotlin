package com.riostudio.eventssdk

import retrofit2.Retrofit

/**
 * Created by FCD on 30/05/2018.
 */
interface EventApiConfig {
    fun setupConfig(builder: Retrofit.Builder)
}