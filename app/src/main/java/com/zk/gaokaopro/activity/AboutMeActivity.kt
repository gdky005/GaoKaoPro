package com.zk.gaokaopro.activity

import android.content.DialogInterface
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.zk.gaokaopro.GKConstant
import com.zk.gaokaopro.R
import kotlinx.android.synthetic.main.layout_about_me.*
import team.zhuoke.sdk.Constant
import team.zhuoke.sdk.base.BaseActivity


class AboutMeActivity : BaseActivity() {

    override fun initListener() {
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_about_me
    }

    override fun initViews() {
        setBarState(rlAboutMe)
        BarUtils.addMarginTopEqualStatusBarHeight(rlAboutMe)
    }

    override fun initData() {
    }

    fun logoClick(view : View) {

        var data  = ""

        var items = GKConstant.BASE_URL_ITEMS

        val currentUrl = Constant.BASE_API_URL
        var currentNum = 0
        for ((index, value) in items.withIndex()) {
            if (value == currentUrl) {
                currentNum = index
                data = value
                break
            }
        }

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("请选择 App 当前运行环境")
        dialog.setSingleChoiceItems(items, currentNum) { _: DialogInterface, position: Int ->
            data = items[position]
        }

        dialog.setPositiveButton("确定") { dialog, which ->
            if (TextUtils.isEmpty(data)) {
                return@setPositiveButton
            }
            Constant.BASE_API_URL = data
            ToastUtils.showShort("真正运行的是：$data")
            dialog.dismiss()
        }
        dialog.setNegativeButton("取消") { dialog, which ->
            dialog.dismiss()
        }
        dialog.create().show()
    }
}