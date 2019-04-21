package com.zk.gaokaopro.activity

import android.util.Log
import com.blankj.utilcode.util.FragmentUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zk.gaokaopro.R
import com.zk.gaokaopro.fragment.HomeFragment
import com.zk.gaokaopro.fragment.ListFragment
import com.zk.gaokaopro.fragment.MeFragment
import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.request.RequestLogin
import com.zk.gaokaopro.model.response.ResponseLogin
import com.zk.gaokaopro.net.BaseHttpObserver
import com.zk.gaokaopro.net.requestmanager.LoginManager
import kotlinx.android.synthetic.main.activity_main.*
import team.zhuoke.sdk.base.BaseActivity
import team.zhuoke.sdk.base.BaseFragment

class MainActivity : BaseActivity() {
    override fun initListener() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    val TAG: String = "MainActivity"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                addFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                addFragment(ListFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                addFragment(MeFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun initViews() {
        setBarState(rootView)
    }

    override fun initData() {

        //TODO for test
        LoginManager.login(RequestLogin("sunny","1132")).subscribe(object : BaseHttpObserver<GKBaseBean<ResponseLogin>>(){
            override fun onError(e: Throwable) {
                Log.d(TAG,"onError")
            }

            override fun onNext(t: GKBaseBean<ResponseLogin>) {
                Log.d(TAG,"xxxxxonNext")
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }
        })

        FragmentUtils.add(supportFragmentManager, HomeFragment(), R.id.container)
    }


    private fun addFragment(fragment: BaseFragment) {
        FragmentUtils.replace(supportFragmentManager, fragment, R.id.container)
    }

    private fun logD(s: String) {
        Log.d("TAG", s)
    }

    private fun oldRequest() {
        //旧的网络层使用方法
        //                gkApi.requestRecommend().enqueue(object : Callback<GKBaseBean<ArrayList<RecommendBean>>> {
        //                    override fun onFailure(call: Call<GKBaseBean<ArrayList<RecommendBean>>>, t: Throwable) {
        //                        message.text = t.message
        //                    }
        //
        //                    override fun onResponse(
        //                        call: Call<GKBaseBean<ArrayList<RecommendBean>>>,
        //                        response: Response<GKBaseBean<ArrayList<RecommendBean>>>
        //                    ) {
        //                        message.text = response.body()!!.result.toString()
        //                    }
        //
        //                })
    }
}