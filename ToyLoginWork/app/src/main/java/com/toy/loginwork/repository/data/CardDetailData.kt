package com.toy.loginwork.repository.data

import com.google.gson.annotations.SerializedName

data class CardDetailData(
    var card: PopularCardData,
    var user: PopularUserData,
    @SerializedName("recommend_cards") var recommendCards: List<PopularCardData>,
    @SerializedName("ok") var success: Boolean
)
