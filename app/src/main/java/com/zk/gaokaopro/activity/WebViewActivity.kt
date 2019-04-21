package com.zk.gaokaopro.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.view.View
import android.webkit.*
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.yanzhenjie.permission.AndPermission
import com.zk.gaokaopro.GKConstant
import com.zk.gaokaopro.R
import kotlinx.android.synthetic.main.layout_webview.*
import team.zhuoke.sdk.base.BaseActivity
import java.net.URLDecoder

class WebViewActivity : BaseActivity() {

    private lateinit var url: String

    override fun getLayoutId(): Int {
        return R.layout.layout_webview
    }

    override fun initListener() {
    }

    override fun initViews() {
        setBarState(webViewRootView)
        BarUtils.addMarginTopEqualStatusBarHeight(webViewRootView)
    }


    override fun initData() {
        val bundle = intent.extras
        if (bundle != null) {
            url = bundle.getString(GKConstant.FLAG_WEBVIEW_URL, "")
        }
        try {
            url = URLDecoder.decode(url, "utf-8")
        } catch (e: Exception) {

        }

        initWebViewSettings()
        webView.loadUrl(url)
    }

    private fun initWebViewSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        webView.webViewClient = object : GKViewClient() {}
        webView.webChromeClient = object : GKWebChromeClient() {}

        val webSettings: WebSettings = webView.settings
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webSettings.loadWithOverviewMode = true
        webSettings.javaScriptEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webSettings.domStorageEnabled = true
    }


    private open inner class GKViewClient : WebViewClient() {

        private var mIsError: Boolean = false

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return filterUri(this@WebViewActivity, request.url)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            val uri = Uri.parse(url)
            return filterUri(this@WebViewActivity, uri)
        }

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler?, error: SslError) {
//            handler.cancel() //super中默认的处理方式，WebView变成空白页
            handler?.proceed()
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            mIsError = true
        }

        override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
            mIsError = true
        }
    }

    fun filterUri(context: Context, uri: Uri?): Boolean {
        if (uri == null) return false
        val url = uri.toString()
        this.url = url
        // 打电话
        if (url.contains("tel:")) {
            checkPermission()
            return true
        }
        return false
    }


    private fun checkPermission() {
        val permissions = arrayOf(Manifest.permission.CALL_PHONE)
        if (AndPermission.hasPermissions(this, permissions)) {
            callPhone()
        } else {
            AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .onGranted {
                    // Storage permission are allowed.
                    callPhone()
                }
                .onDenied {
                    ToastUtils.showShort("获取权限失败")
                }
                .start()

        }
    }

    private fun callPhone() {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse(url))
        startActivity(intent)
    }

    private open inner class GKWebChromeClient : WebChromeClient() {

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
        }

        override fun onShowCustomView(view: View, callback: WebChromeClient.CustomViewCallback) {
            super.onShowCustomView(view, callback)
        }
    }

    inner class NorthStartJsInterface(val context: Context) {

        /**
         * 获取token .
         */
        @JavascriptInterface
        fun getToken(): String? {
//            return UserInfoManager.instance.getUserAccessToken()
            return ""
        }

    }

}