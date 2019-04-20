package com.zk.gaokaopro.activity

import android.util.Log
import com.blankj.utilcode.util.FragmentUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zk.gaokaopro.R
import com.zk.gaokaopro.fragment.HomeFragment
import com.zk.gaokaopro.fragment.ListFragment
import com.zk.gaokaopro.fragment.MeFragment
import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.RecommendBean
import com.zk.gaokaopro.model.request.RequestLogin
import com.zk.gaokaopro.model.response.ResponseLogin
import com.zk.gaokaopro.net.BaseHttpObserver
import com.zk.gaokaopro.net.requestmanager.LoginManager
import com.zk.gaokaopro.viewModel.BaseViewModel
import com.zk.gaokaopro.viewModel.RecommendViewModel
import kotlinx.android.synthetic.main.activity_main.*
import team.zhuoke.sdk.base.BaseFragment

class MainActivity : BaseActivity() {
    val TAG: String = "MainActivity"

    private val recommendViewModel = RecommendViewModel()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                addFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.text = "请求网络数据"
                addFragment(ListFragment())
                recommendViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<RecommendBean>>{
                    override fun success(result: ArrayList<RecommendBean>?) {
                        message.text = result.toString()
                        logD("请求数据是：${result.toString()}")
                    }
                })
                recommendViewModel.requestData()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                addFragment(MeFragment())
                message.setText(R.string.title_me)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
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

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun getIntentData() {

//        ZKConnectionManager.getInstance().getApi()
    }

    override fun getViewModel() {
    }

    override fun setViewModelObserve() {
    }

    override fun initViews() {
    }

    override fun setListener() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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
}