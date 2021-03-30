package com.toy.loginwork.repository.data

import com.google.gson.annotations.SerializedName

data class PictureData(
    @SerializedName("ok")
    var success: Boolean,
    @SerializedName("cards") var popularCards: List<PopularCardData>,
)
