package com.zk.gaokaopro.model

import com.google.gson.annotations.SerializedName
import com.zk.gaokaopro.api.GKHttpStatusCode.Companion.REQUEST_NET_ERROR


data class GKBaseBean<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val msg: String,
    @SerializedName("total") val total: Int,
    @SerializedName("result") val result: T?
) {

    companion object {
        fun <T> success(msg: String, total: Int, data: T?) = GKBaseBean(0, msg, total, data)

        fun <T> error(errorCode: Int, msg: String, data: T?) = GKBaseBean(errorCode, msg, 0, data)

        fun <T> otherError(data: T?) = GKBaseBean(
            REQUEST_NET_ERROR,
            "网络出错 或者 数据异常",
            0,
            data
        )

    }
}