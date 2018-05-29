package com.arequa.eventssdk

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import junit.framework.Assert
import org.junit.Test

/**
 * Created by FCD on 30/05/2018.
 */
class RequestTest {

    @Test
    fun eventRequest_success() {

        val apiService = EventApiService()
        val response = apiService.apiClient.getDeals().execute()
        val events = response.body()

        val parser = JsonParser()
        val jsonResponse: JsonArray = parser.parse(MockResponses.DEALS_RESPONSE).asJsonArray

        Assert.assertTrue(response.isSuccessful)

        events?.let {
            Assert.assertEquals(events.size, jsonResponse.size())

            events.zip(jsonResponse).forEach { (event, jsonEvent) ->
                with(jsonEvent.asJsonObject) {
                    Assert.assertEquals(event.title, this["title"].asString)
                    Assert.assertEquals(event.metacriticScore, this["metacriticScore"].asInt)
                    Assert.assertEquals(event.steamRating, this["steamRatingPercent"].asInt)
                    Assert.assertEquals(event.thumb, this["thumb"].asString)
                }

            }
        }


    }

    @Test
    fun alleventRequest_success() {

        val apiService = EventApiService()
        val response = apiService.apiClient.getTopGames().execute()
        val allevents = response.body()

        val parser = JsonParser()
        val jsonResponse: List<JsonObject> = parser.parse(MockResponses.TOP_100_GAMES)
                .asJsonObject
                .entrySet()
                .map { (_, json) ->
                    json.asJsonObject
                }


        Assert.assertTrue(response.isSuccessful)

        allevents?.let {

            allevents.zip(jsonResponse).forEach { (event, jsonEvent) ->
                with(jsonEvent.asJsonObject) {
                    Assert.assertEquals(event.title, this["name"].asString)
                    Assert.assertEquals(event.steamRating, this["score_rank"].asInt)
                    Assert.assertEquals(event.publisher, this["publisher"].asString)
                    Assert.assertEquals(event.owners, this["owners"].asInt)
                    Assert.assertEquals(event.thumb, "http://cdn.akamai.steamstatic" +
                            ".com/steam/apps/${this["appid"]}/capsule_184x69.jpg")
                }

            }
        }


    }

}