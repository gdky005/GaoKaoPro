package com.zk.gaokaopro.model

import com.google.gson.annotations.SerializedName

data class LoginBean(
    @SerializedName("uid") val uid: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String
)