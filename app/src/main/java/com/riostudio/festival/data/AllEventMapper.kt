package com.riostudio.festival.data

import com.riostudio.eventssdk.AllEvent

/**
 * Created by francisco.caro on 06/06/2018.
 */
object AllEventMapper {
    fun fromSdk(event: AllEvent, position: Int): com.riostudio.festival.AllEvent {
        return com.riostudio.festival.AllEvent(event.title, event.owners, event.steamRating, event.publisher,
                event.price, position, event.thumb)
    }
}