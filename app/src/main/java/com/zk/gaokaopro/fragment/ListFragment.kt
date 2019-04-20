package com.zk.gaokaopro.fragment

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.BarUtils
import com.zk.gaokaopro.R
import kotlinx.android.synthetic.main.fragment_list.*
import team.zhuoke.sdk.base.BaseFragment


class ListFragment : BaseFragment() {

    override fun initListener() {

    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun initView(rootView: View) {
        BarUtils.addMarginTopEqualStatusBarHeight(listRootView)
        activity?.let { BarUtils.setStatusBarLightMode(it, true) }
    }
}