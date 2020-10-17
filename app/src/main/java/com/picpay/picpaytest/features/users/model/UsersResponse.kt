package com.picpay.picpaytest.features.users.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("img") val image: String?,
    @SerializedName("username") val userName: String?
)