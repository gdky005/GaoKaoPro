package com.zk.gaokaopro.net

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.zk.gaokaopro.BuildConfig
import com.zk.gaokaopro.GKConstant
import com.zk.gaokaopro.api.GKApi
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitManager {
    private val TAG = "RetrofitManager"

    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    private val baseUrl = GKConstant.BASE_URL

    private constructor() {
        initOkHttp()

        initRetrofit()
    }

    companion object {
        private var retrofitManager: RetrofitManager? = null

        fun init() {
            retrofitManager = RetrofitManager()
        }

        fun getApiService(): GKApi {
            return getApiService(GKApi::class.java)
        }

        fun <T> getApiService(tClass: Class<T>): T {
            if (retrofitManager == null) {
                init()
            }
            synchronized(this.retrofitManager!!) {
                return retrofitManager!!.retrofit!!.create(tClass)
            }
        }
    }

    @Synchronized
    private fun initRetrofit() {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(okHttpClient!!)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(ObserveOnMainCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create()) //must add at the end of other Converter
                .build()
        }
    }

    private fun initOkHttp() {
        val builder = OkHttpClient.Builder()

        // Log http to console.
        if (!BuildConfig.PRODUCTION_EVN || BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        builder.addInterceptor(GaoKaoProInterceptor())
        builder.addNetworkInterceptor(StethoInterceptor())

        // debug环境让抓包方便
        if (BuildConfig.PRODUCTION_EVN) {
            builder.connectTimeout(10, TimeUnit.SECONDS)
            builder.readTimeout(15, TimeUnit.SECONDS)
            builder.writeTimeout(15, TimeUnit.SECONDS)
        } else {
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.writeTimeout(60, TimeUnit.SECONDS)
        }

        okHttpClient = builder.build()
    }

    private inner class GaoKaoProInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            // addQueryParameter
            val originalRequest = chain.request()
            var modifyUrlBuilder: HttpUrl.Builder = originalRequest.url().newBuilder()

            val headers = originalRequest.headers().newBuilder()
            handleRequestHeaders(headers)

            // 继续请求
            val request = originalRequest
                .newBuilder()
                .url(modifyUrlBuilder.build())
                .headers(headers.build())
                .build()
            var response = chain.proceed(request)

            if (BuildConfig.DEBUG) {
                Log.d("okhttp", response.protocol().name)
            }

            return response
        }

        /**
         * 处理请求的headers
         *
         * @param headers
         */
        private fun handleRequestHeaders(headers: Headers.Builder) {
        }
    }
}