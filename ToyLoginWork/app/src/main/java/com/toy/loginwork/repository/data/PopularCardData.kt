package com.toy.loginwork.repository.data

import com.google.gson.annotations.SerializedName

data class PopularCardData(
    @SerializedName("user_id") var userId: Long, @SerializedName("img_url")
    var imgUrl: String, var description: String, var id: Long
)
