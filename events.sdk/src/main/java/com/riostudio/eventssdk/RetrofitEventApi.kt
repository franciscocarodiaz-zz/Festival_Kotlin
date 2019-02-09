package com.riostudio.eventssdk

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

/**
 * Created by FCD on 28/05/2018.
 */
interface RetrofitEventApi {

    @GET(Routes.GET_DEALS)
    fun getDeals(): Call<ArrayList<Event>>

    @GET(Routes.GET_TOP_100_GAMES)
    fun getTopGames(): Call<ArrayList<AllEvent>>

    @GET(Routes.GET_DEALS)
    fun getDealsObservable(): io.reactivex.Observable<ArrayList<Event>>

    @GET(Routes.GET_TOP_100_GAMES)
    fun getTopGamesObservable(): io.reactivex.Observable<ArrayList<AllEvent>>

}