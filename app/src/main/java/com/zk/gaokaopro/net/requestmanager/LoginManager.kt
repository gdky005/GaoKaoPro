package com.zk.gaokaopro.net.requestmanager

import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.request.RequestLogin
import com.zk.gaokaopro.model.response.ResponseLogin
import com.zk.gaokaopro.net.RetrofitManager
import io.reactivex.Observable

object LoginManager {
    fun login(login: RequestLogin): Observable<GKBaseBean<ResponseLogin>> {
        return RetrofitManager.getApiService().login(login)
    }
}