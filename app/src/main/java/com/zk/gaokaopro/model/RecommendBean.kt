package com.zk.gaokaopro.model

import com.google.gson.annotations.SerializedName

data class RecommendBean(
    @SerializedName("title") val title: String,
    @SerializedName("imgUrl") val imgUrl: String,
    @SerializedName("url") val url: String
)