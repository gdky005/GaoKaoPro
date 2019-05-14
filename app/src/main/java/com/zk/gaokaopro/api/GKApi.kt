package com.zk.gaokaopro.api

import com.zk.gaokaopro.model.*
import com.zk.gaokaopro.model.request.RequestLogin
import com.zk.gaokaopro.model.response.ResponseLogin
import io.reactivex.Observable
import retrofit2.http.*

interface GKApi {

    @GET(UrlConfig.URL_TEST)
//    fun requestTest(): Call<GKBaseBean<String>>
    fun requestTest(): Observable<GKBaseBean<String>>

    @GET(UrlConfig.URL_RECOMMEND)
    fun requestRecommend(): Observable<GKBaseBean<ArrayList<RecommendBean>>>

    @GET(UrlConfig.URL_CATEGORY)
    fun requestCategory(): Observable<GKBaseBean<ArrayList<CategoryBean>>>

    @GET(UrlConfig.URL_NEWS_LIST)
    fun requestNewList(@Query("page") page: Int, @Query("pageCount") pageCount: Int): Observable<GKBaseBean<ArrayList<NewsListBean>>>

    @GET(UrlConfig.URL_LIST)
    fun requestList(@Query("page") page: Int, @Query("pageCount") pageCount: Int): Observable<GKBaseBean<ArrayList<ListBean>>>


    @POST(UrlConfig.URL_LOGIN)
    @FormUrlEncoded
    fun requestLogin(@Field("account") userName: String, @Field("password")  password: String): Observable<GKBaseBean<LoginBean>>


    @POST(UrlConfig.URL_REGISTER)
    @FormUrlEncoded
    fun requestRegister(@Field("account") userName: String, @Field("password")  password: String,
                        @Field("password2")  passwordNew: String): Observable<GKBaseBean<LoginBean>>

    @POST(UrlConfig.URL_USER_LOGIN)
    fun login(@Body login: RequestLogin): Observable<GKBaseBean<ResponseLogin>>

    //消息详情页面
    @GET(UrlConfig.URL_MSG_DETAIL)
    fun requestMsgDetail(@Query("id") id: Int): Observable<GKBaseBean<ListBean>>

    //写消息界面
    @POST(UrlConfig.URL_WRITE_MSG)
    @FormUrlEncoded
    fun requestWriteMsg(@Field("uid") uid: Int, @Field("author") author: String, @Field("title")  title: String,
                        @Field("summary")  summary: String, @Field("des")  des: String): Observable<GKBaseBean<ListBean>>



    /**
     * 测试域名
     */
    @GET(UrlConfig.URL_TEST)
    fun requestTestAddParams(@Query("id") id: String): Observable<GKBaseBean<String>>
}