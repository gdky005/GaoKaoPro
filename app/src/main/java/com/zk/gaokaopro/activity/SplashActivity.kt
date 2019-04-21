package com.zk.gaokaopro.activity

import android.Manifest
import android.content.Intent
import android.os.Handler
import com.yanzhenjie.permission.AndPermission
import com.zk.gaokaopro.R
import kotlinx.android.synthetic.main.layout_splash.*
import team.zhuoke.sdk.base.BaseActivity

class SplashActivity : BaseActivity() {

    companion object {
        const val DELAY_TIME: Long = 1000L

        const val FLAG_ENTER_MAIN: Int = 0
        const val FLAG_REQUEST_PERMISSION: Int = 100
    }

    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private var splashHandler : Handler = Handler {
        if (it.what == FLAG_ENTER_MAIN) {
            startMainActivity()
            finish()
        }

        true
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_splash
    }
    override fun initViews() {
        setBarState(splashContainer)
    }

    override fun initListener() {
    }


    override fun initData() {
        permissionCheck()
//        splashHandler.sendEmptyMessageDelayed(FLAG_ENTER_MAIN, DELAY_TIME)
    }

    //####################################### 权限 startActivity ############################################
    private fun permissionCheck() {
        if (!AndPermission.hasPermissions(this, permissions)) {
            AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .onGranted {
                    // Storage permission are allowed.
                    splashHandler.sendEmptyMessageDelayed(
                        FLAG_ENTER_MAIN,
                        DELAY_TIME
                    )
                }
                .onDenied {
//                    toast(getString(R.string.question_permission_tip))
//                    todo
                    splashHandler.sendEmptyMessageDelayed(
                        FLAG_ENTER_MAIN,
                        DELAY_TIME
                    )
                }
                .start()
        } else {
            splashHandler.sendEmptyMessageDelayed(
                FLAG_ENTER_MAIN,
                DELAY_TIME
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        splashHandler.removeMessages(FLAG_ENTER_MAIN)
    }

    private fun startMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    }
}