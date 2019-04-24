package com.zk.gaokaopro.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.blankj.utilcode.util.BarUtils
import com.zk.gaokaopro.R
import com.zk.gaokaopro.activity.AboutMeActivity
import com.zk.gaokaopro.activity.LoginActivity
import com.zk.gaokaopro.manager.UserInfoManager
import kotlinx.android.synthetic.main.fragment_me.*
import team.zhuoke.sdk.base.BaseFragment

class MeFragment : BaseFragment(), View.OnClickListener {

    lateinit var btLogin: Button
    lateinit var btAboutMe: Button
    lateinit var btLogout: Button
    lateinit var llLogin: LinearLayout
    lateinit var userLogo: ImageView
    lateinit var userName: TextView

    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initView(rootView: View) {
        BarUtils.addMarginTopEqualStatusBarHeight(meRootView)

        btLogin = rootView.findViewById(R.id.btLogin)
        btAboutMe = rootView.findViewById(R.id.btAboutMe)
        btLogout = rootView.findViewById(R.id.btLogout)
        llLogin = rootView.findViewById(R.id.ll_login)
        userLogo = rootView.findViewById(R.id.userLogo)
        userName = rootView.findViewById(R.id.userName)
    }

    override fun initListener() {
        btLogin.setOnClickListener(this)
        btAboutMe.setOnClickListener(this)
        btLogout.setOnClickListener(this)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        refreshUserLogin()
    }

    private fun refreshUserLogin() {
        if (UserInfoManager.instance.isLogin()) {
            btLogin.visibility = View.GONE
            btLogout.visibility = View.VISIBLE
            userName.visibility = View.VISIBLE

            userName.text = UserInfoManager.instance.getUserName()

        } else {
            btLogin.visibility = View.VISIBLE
            btLogout.visibility = View.GONE
            userName.visibility = View.GONE
        }
    }


    private fun login() {
        startActivity(Intent(activity, LoginActivity::class.java))
    }

    private fun logout() {
        UserInfoManager.instance.clear()
        refreshUserLogin()
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

            R.id.btLogout -> {
                logout()
            }
            else -> {


            }
        }
    }
}