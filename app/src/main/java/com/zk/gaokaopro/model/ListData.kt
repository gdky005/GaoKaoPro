package com.zk.gaokaopro.model

import com.google.gson.annotations.SerializedName

data class ListBean(
    @SerializedName("id") val id: Int,
    @SerializedName("uid") val uid: Int,
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("des") val des: String,
    @SerializedName("createTime") val createTime: Long
)