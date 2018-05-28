package com.arequa.festival2018

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
                    var steamRating: Int,
                    var publisher: Int,
                    var price: Float,
                    var position: Int,
                    var thumb: String) {

    val priceFormatted : String
        get() = PriceFormatter.salePriceFormatted(price)

}