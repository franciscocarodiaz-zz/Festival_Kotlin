package com.arequa.eventssdk.serializer

import com.arequa.eventssdk.AllEvent
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Created by FCD on 29/05/2018.
 */
class AllEventDeserializer : JsonDeserializer<AllEvent> {

    companion object {
        const val BASE_IMG_URL = "http://cdn.akamai.steamstatic.com/steam/apps/%s/capsule_184x69.jpg"
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): AllEvent {
        val gson = Gson()

        val allEvent = gson.fromJson(json, AllEvent::class.java)

        val jsonItem = json.asJsonObject
        val appId = jsonItem["appid"].asInt.toString()

        val rawRating = jsonItem["score_rank"].asString
        val steamRating = if(rawRating.isEmpty()) 0 else Integer.parseInt(rawRating)
        allEvent.steamRating = steamRating

        val thumb = String.format(BASE_IMG_URL, appId)
        allEvent.thumb = thumb

        allEvent.price = allEvent.price / 100

        return allEvent
    }

}