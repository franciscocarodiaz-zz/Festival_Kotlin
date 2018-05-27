package com.arequa.festival2018

/**
 * Created by FCD on 27/05/2018.
 */
data class Event(var title: String,
                 var salePrice: Float,
                 var normalPrice: Float,
                 var metacriticScore: Int,
                 var steamRating: Int,
                 var thumb: String)

data class AllEvent(var title: String,
                    var owners: Int,
                    var steamRating: Int,
                    var publisher: Int,
                    var price: Float,
                    var position: Int,
                    var thumb: String)