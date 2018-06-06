package com.arequa.festival2018.data

import com.arequa.eventssdk.Event

/**
 * Created by francisco.caro on 06/06/2018.
 */
object EventMapper {
    fun fromSdk(event: Event): com.arequa.festival2018.Event {
        return com.arequa.festival2018.Event(event.title, event.salePrice, event.normalPrice, event.metacriticScore,
                event.steamRating, event.thumb)
    }
}