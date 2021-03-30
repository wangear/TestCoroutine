package com.toy.loginwork.repository.data

import com.google.gson.annotations.SerializedName

data class UserDetailData(
    var user: PopularUserData,
    @SerializedName("ok") var success: Boolean
)
