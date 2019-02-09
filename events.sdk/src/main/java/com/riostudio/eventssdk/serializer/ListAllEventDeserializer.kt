package com.riostudio.eventssdk.serializer

import com.riostudio.eventssdk.AllEvent
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Created by FCD on 30/05/2018.
 */
class ListAllEventDeserializer: JsonDeserializer<ArrayList<AllEvent>> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): ArrayList<AllEvent> {
        val jsonAllEvent = json.asJsonObject
                .entrySet()
                .map { (_, json) ->
                    json.asJsonObject
                }
        val gson = GsonBuilder()
                .registerTypeAdapter(AllEvent::class.java, AllEventDeserializer())
                .create()
        val listAllEvents: List<AllEvent> = jsonAllEvent.map { jsonList ->
            gson.fromJson(jsonList, AllEvent::class.java)
        }

        val arraylistAllEvent : ArrayList<AllEvent> = arrayListOf()
        arraylistAllEvent.addAll(listAllEvents)

        return arraylistAllEvent
    }
}