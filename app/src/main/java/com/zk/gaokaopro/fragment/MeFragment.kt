package com.zk.gaokaopro.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.zk.gaokaopro.R
import kotlinx.android.synthetic.main.fragment_me.*
import team.zhuoke.sdk.base.BaseFragment

class MeFragment : BaseFragment(), View.OnClickListener {

    lateinit var btLogin: Button

    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initView(rootView: View) {
        BarUtils.addMarginTopEqualStatusBarHeight(meRootView)

        btLogin = rootView.findViewById(R.id.btLogin)
    }

    override fun initListener() {
        btLogin.setOnClickListener(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    fun login() {

        ToastUtils.showShort("准备登录")
    }


    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.btLogin -> {
                login()
            }
            else -> {

            }
        }
    }
}