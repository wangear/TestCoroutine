package com.toy.loginwork.repository.data

import com.google.gson.annotations.SerializedName

data class HomeData(
    @SerializedName("popular_cards") var popularCards: List<PopularCardData>,
    @SerializedName("popular_users") var popularUsers: List<PopularUserData>, @SerializedName("ok")
    var success: Boolean
)
