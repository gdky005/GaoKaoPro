package com.zk.gaokaopro.manager

import com.zk.gaokaopro.api.GKApi
import team.zhuoke.sdk.manager.ZKConnectionManager

class NetManager  private constructor()  {

    private var gkApi: GKApi? = null

    companion object {
        val instance: NetManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetManager()
        }
    }

    fun getGKApi(): GKApi {
        if (gkApi == null) {
            val connectionManager = ZKConnectionManager.getInstance()
            gkApi = connectionManager.getApi(GKApi::class.java) as GKApi
        }
        return gkApi!!
    }
}