package com.arequa.eventssdk

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
                    var publisher: String,
                    @SerializedName("score_rank") var steamRating: String,
                    var owners: Int,
                    var price: Float,
                    var thumb: String)
