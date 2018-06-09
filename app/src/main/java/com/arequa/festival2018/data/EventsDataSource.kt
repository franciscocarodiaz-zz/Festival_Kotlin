package com.arequa.festival2018.data

import com.arequa.eventssdk.AllEvent
import com.arequa.eventssdk.Event
import io.reactivex.Observable
import com.arequa.eventssdk.EventApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

/**
 * Created by francisco.caro on 06/06/2018.
 */
object EventsDataSource {

    val apiService = EventApiService()

    fun getEvents(): Observable<ArrayList<com.arequa.festival2018.Event>> {
        return apiService.apiClient
                .getDealsObservable()
                .map { listEvent ->
                    val events = listEvent.map { event -> EventMapper.fromSdk(event) }
                    val arrayListEvents = arrayListOf<com.arequa.festival2018.Event>()
                    arrayListEvents.addAll(events)
                    arrayListEvents
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getAllEvents(): Observable<ArrayList<com.arequa.festival2018.AllEvent>> {
        return apiService.apiClient
                .getTopGamesObservable()
                .map { listAllEvents ->
                    val events = listAllEvents.mapIndexed { index, alleventItem ->
                        AllEventMapper.fromSdk(alleventItem, index + 1) }
                    val arrayListEvents = arrayListOf<com.arequa.festival2018.AllEvent>()
                    arrayListEvents.addAll(events)
                    arrayListEvents
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}