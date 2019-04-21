package com.zk.gaokaopro.model

import com.google.gson.annotations.SerializedName

data class HomeListBean(
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)