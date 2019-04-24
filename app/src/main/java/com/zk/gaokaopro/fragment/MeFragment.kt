package com.zk.gaokaopro.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blankj.utilcode.util.BarUtils
import com.zk.gaokaopro.R
import com.zk.gaokaopro.activity.AboutMeActivity
import com.zk.gaokaopro.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_me.*
import team.zhuoke.sdk.base.BaseFragment

class MeFragment : BaseFragment(), View.OnClickListener {

    lateinit var btLogin: Button
    lateinit var btAboutMe: Button

    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initView(rootView: View) {
        BarUtils.addMarginTopEqualStatusBarHeight(meRootView)

        btLogin = rootView.findViewById(R.id.btLogin)
        btAboutMe = rootView.findViewById(R.id.btAboutMe)
    }

    override fun initListener() {
        btLogin.setOnClickListener(this)
        btAboutMe.setOnClickListener(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    private fun login() {
        startActivity(Intent(activity, LoginActivity::class.java))
    }

    private fun aboutMe() {
        startActivity(Intent(activity, AboutMeActivity::class.java))
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btLogin -> {
                login()
            }

            R.id.btAboutMe -> {
                aboutMe()
            }
            else -> {


            }
        }
    }
}