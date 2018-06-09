package com.arequa.festival2018

import java.io.Serializable
import java.util.*

/**
 * Created by FCD on 27/05/2018.
 */

object PriceFormatter {
    val FORMAT_PRICE = "$%.2f"

    fun salePriceFormatted(price: Float) = String.format(FORMAT_PRICE, price)
}

data class Event(var title: String,
                 var salePrice: Float,
                 var normalPrice: Float,
                 var metacriticScore: Int,
                 var steamRating: Int,
                 var thumb: String) {

    val salePriceFormatted : String
        get() = PriceFormatter.salePriceFormatted(salePrice)

    val normalPriceFormatted : String
        get() = PriceFormatter.salePriceFormatted(normalPrice)
}

data class AllEvent(var title: String,
                    var owners: Int,
                    var publisher: String,
                    var steamRating: Int,
                    var price: Float,
                    var position: Int,
                    var thumb: String) {

    val priceFormatted : String
        get() = PriceFormatter.salePriceFormatted(price)

}

data class EventDetail(
        var id: String = UUID.randomUUID().toString(),
        var name: String,
        var born: String,
        var title: String,
        var actor: String,
        var quote: String,
        var father: String,
        var mother: String,
        var spouse: String,
        var img: String,
        var category: EventCategory)

data class EventCategory(
        var name: String,
        var region: String,
        var words: String,
        var img: String) : Serializable {

    companion object {
        private val DEFAULT_PALETTE = arrayOf(R.color.starkOverlay, R.color.starkBase, R.drawable.ic_stark)
        private val resources = mapOf(
                Pair("stark", arrayOf(R.color.starkOverlay, R.color.starkBase, R.drawable.ic_stark)),
                Pair("lannister", arrayOf(R.color.lannisterOverlay, R.color.lannisterBase, R.drawable.ic_lannister)),
                Pair("tyrrel", arrayOf(R.color.tyrellOverlay, R.color.tyrellBase, R.drawable.ic_tyrell)),
                Pair("arryn", arrayOf(R.color.arrynOverlay, R.color.arrynBase, R.drawable.ic_arryn)),
                Pair("targaryen", arrayOf(R.color.targaryenOverlay, R.color.targaryenBase, R.drawable.ic_targaryen)),
                Pair("martell", arrayOf(R.color.martellOverlay, R.color.martellBase, R.drawable.ic_martell)),
                Pair("baratheon", arrayOf(R.color.baratheonOverlay, R.color.baratheonBase, R.drawable.ic_baratheon)),
                Pair("greyjoy", arrayOf(R.color.greyjoyOverlay, R.color.greyjoyBase, R.drawable.ic_greyjoy)),
                Pair("frey", arrayOf(R.color.freyOverlay, R.color.freyBase, R.drawable.ic_frey)),
                Pair("tully", arrayOf(R.color.tullyOverlay, R.color.tullyBase, R.drawable.ic_tully))
        )

        fun getOverlayColor(houseId: String): Int {
            var color = resources[houseId]
            if (color == null)
                color = DEFAULT_PALETTE

            return color[0]
        }

        fun getBaseColor(houseId: String): Int {
            var color = resources[houseId]
            if (color == null)
                color = DEFAULT_PALETTE

            return color[1]
        }

        fun getIcon(houseId: String) : Int {
            var icon = resources[houseId]
            if (icon == null)
                icon = DEFAULT_PALETTE

            return icon[2]
        }

    }

}