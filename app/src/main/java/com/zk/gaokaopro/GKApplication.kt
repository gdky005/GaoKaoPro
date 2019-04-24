package com.zk.gaokaopro

import android.app.Application
import com.zk.gaokaopro.api.UrlConfig
import team.zhuoke.sdk.ZKManager

class GKApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val zkManager = ZKManager.getInstance()

        zkManager.init(this, BuildConfig.DEBUG) //必须
        zkManager.initStethoForApplicaiton(this) //可选 手机 USB 连接电脑，可以通过 Chrome 中输入：chrome://inspect,查看网络，资源，数据库，SP 文件
        zkManager.initTakePhotoForApplication()  //推荐添加 兼容7.0以上无法拍照问题
        zkManager.setBaseUrl(UrlConfig.getBaseUrl()) //必选，设置当前 app 请求域名的 Url

    }
}