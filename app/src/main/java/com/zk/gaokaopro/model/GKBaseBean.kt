package com.zk.gaokaopro.model

import com.google.gson.annotations.SerializedName
import com.zk.gaokaopro.api.GKHttpStatusCode.Companion.REQUEST_NET_ERROR


data class GKBaseBean<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("msg") val msg: String,
    @SerializedName("result") val result: T?
) {

    companion object {
        fun <T> success(data: T?) = GKBaseBean(0, "", data)

        fun <T> error(errorCode: Int, msg: String, data: T?) = GKBaseBean(errorCode, msg, data)

        fun <T> otherError(data: T?) = GKBaseBean(
            REQUEST_NET_ERROR,
            "网络出错",
            data
        )

    }
}