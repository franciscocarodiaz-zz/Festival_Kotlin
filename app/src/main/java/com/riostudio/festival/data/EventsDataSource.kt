package com.riostudio.festival.data

import io.reactivex.Observable
import com.riostudio.eventssdk.EventApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

/**
 * Created by francisco.caro on 06/06/2018.
 */
object EventsDataSource {

    val apiService = EventApiService()

    fun getEvents(): Observable<ArrayList<com.riostudio.festival.Event>> {
        return apiService.apiClient
                .getDealsObservable()
                .map { listEvent ->
                    val events = listEvent.map { event -> EventMapper.fromSdk(event) }
                    val arrayListEvents = arrayListOf<com.riostudio.festival.Event>()
                    arrayListEvents.addAll(events)
                    arrayListEvents
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getAllEvents(): Observable<ArrayList<com.riostudio.festival.AllEvent>> {
        return apiService.apiClient
                .getTopGamesObservable()
                .map { listAllEvents ->
                    val events = listAllEvents.mapIndexed { index, alleventItem ->
                        AllEventMapper.fromSdk(alleventItem, index + 1) }
                    val arrayListEvents = arrayListOf<com.riostudio.festival.AllEvent>()
                    arrayListEvents.addAll(events)
                    arrayListEvents
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}