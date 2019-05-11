package com.zk.gaokaopro.model

import com.google.gson.annotations.SerializedName

data class LoginBean(
    @SerializedName("user_id") val uid: Int,
    @SerializedName("username") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String
)