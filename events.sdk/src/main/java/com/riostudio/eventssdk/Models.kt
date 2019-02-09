package com.riostudio.eventssdk

import com.google.gson.annotations.SerializedName

/**
 * Created by FCD on 28/05/2018.
 */

data class Event(var title: String,
                 var salePrice: Float,
                 var normalPrice: Float,
                 var metacriticScore: Int,
                 @SerializedName("steamRatingPercent") var steamRating: Int,
                 var thumb: String)

data class AllEvent(@SerializedName("name") var title: String,
                    var owners: Int,
                    var steamRating: Int,
                    var publisher: String,
                    var price: Float,
                    var position: Int,
                    var thumb: String)

