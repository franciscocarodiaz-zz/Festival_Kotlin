package com.riostudio.festival.data

import com.riostudio.eventssdk.Event

/**
 * Created by francisco.caro on 06/06/2018.
 */
object EventMapper {
    fun fromSdk(event: Event): com.riostudio.festival.Event {
        return com.riostudio.festival.Event(event.title, event.salePrice, event.normalPrice, event.metacriticScore,
                event.steamRating, event.thumb)
    }
}