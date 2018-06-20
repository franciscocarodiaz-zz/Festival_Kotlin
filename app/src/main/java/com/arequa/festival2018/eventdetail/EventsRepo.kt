package com.arequa.festival2018.eventdetail

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.arequa.festival2018.EventCategory
import com.arequa.festival2018.EventDetail
import com.arequa.festival2018.MainActivity.Companion.OPTION_GROUP_LEFT
import com.arequa.festival2018.MainActivity.Companion.OPTION_GROUP_MIDDLE
import org.json.JSONArray
import org.json.JSONObject


object EventsRepo {

    private val URL_EVENTS: String = "http://5b16590aa1c7e300147c871a.mockapi.io/characters"
    private var idRepo: Int = 0
    val events: HashMap<Int, MutableList<EventDetail>> = hashMapOf(
            Pair(OPTION_GROUP_LEFT, mutableListOf()),
            Pair(OPTION_GROUP_MIDDLE, mutableListOf())
    )

    fun requestEventDetails(context: Context,
                          id: Int,
                          success: ((MutableList<EventDetail>) -> Unit),
                          error: (()-> Unit)) {
        idRepo = id
        if (events().isEmpty()) {
            val request = JsonArrayRequest(Request.Method.GET, URL_EVENTS, null,
                                    {response ->
                                        events[id] = parseEvents(response)
                                        if (id == OPTION_GROUP_LEFT) {
                                            events().addAll(parseEvents(response))
                                        }
                                        success.invoke(events())
                                    },
                                    {_ ->
                                        error.invoke()
                                    })

            Volley.newRequestQueue(context)
                    .add(request)
        } else {
            success.invoke(events())
        }

    }

    private fun events(): MutableList<EventDetail> {
        return events[idRepo]!!
    }

    private fun parseEvents(jsonArray: JSONArray) : MutableList<EventDetail> {
        val characters = mutableListOf<EventDetail>()
        for (index in 0..(jsonArray.length()-1)){
            val event = parseEvent(jsonArray.getJSONObject(index))
            characters.add(event)
        }
        return characters
    }

    private fun parseEvent(jsonCharacter: JSONObject) : EventDetail{
        return EventDetail(jsonCharacter.getString("id"),
                jsonCharacter.getString("name"),
                jsonCharacter.getString("born"),
                jsonCharacter.getString("title"),
                jsonCharacter.getString("actor"),
                jsonCharacter.getString("quote"),
                jsonCharacter.getString("father"),
                jsonCharacter.getString("mother"),
                jsonCharacter.getString("spouse"),
                jsonCharacter.getString("img"),
                parseHouse(jsonCharacter.getJSONObject("house")))
    }

    private fun parseHouse(data: JSONObject) : EventCategory {
        return EventCategory(data.getString("name"),
                data.getString("region"),
                data.getString("words"),
                data.getString("img"))
    }

    fun findCharacterById(id: String) : EventDetail? {
        return events().find { eventDetail ->
            eventDetail.id == id
        }
    }

}