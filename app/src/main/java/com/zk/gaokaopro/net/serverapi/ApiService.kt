package com.zk.gaokaopro.net.serverapi

import com.zk.gaokaopro.api.UrlConfig
import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.request.RequestLogin
import com.zk.gaokaopro.model.response.ResponseLogin
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 所有的server请求接口
 */
interface ApiService {
    /**
     * 用户登录接口
     */
    @POST(UrlConfig.URL_USER_LOGIN)
    fun login(@Body login: RequestLogin): Observable<GKBaseBean<ResponseLogin>>
}
