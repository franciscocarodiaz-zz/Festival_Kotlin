package com.arequa.festival2018.data

import com.arequa.eventssdk.AllEvent

/**
 * Created by francisco.caro on 06/06/2018.
 */
object AllEventMapper {
    fun fromSdk(event: AllEvent, position: Int): com.arequa.festival2018.AllEvent {
        return com.arequa.festival2018.AllEvent(event.title, event.owners, event.steamRating, event.publisher,
                event.price, position, event.thumb)
    }
}