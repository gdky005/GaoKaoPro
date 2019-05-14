package com.zk.gaokaopro.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.BarUtils
import com.zk.gaokaopro.GKConstant
import com.zk.gaokaopro.R
import com.zk.gaokaopro.activity.WebViewActivity
import com.zk.gaokaopro.adapter.ListAdapter
import com.zk.gaokaopro.model.ListBean
import com.zk.gaokaopro.viewModel.BaseViewModel
import com.zk.gaokaopro.viewModel.list.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import team.zhuoke.sdk.base.BaseFragment


class ListFragment : BaseFragment() {

    private val listViewModel = ListViewModel()
    val homeNewsListAdapter = ListAdapter(null)

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun initView(rootView: View) {
        BarUtils.addMarginTopEqualStatusBarHeight(listRootView)

        listZKRecyclerView.layoutManager = LinearLayoutManager(activity)
        listZKRecyclerView.adapter = homeNewsListAdapter
        homeNewsListAdapter.setOnItemClickListener { adapter, view, position ->
            val listBean = adapter.data[position] as ListBean
//            startWebViewActivity(listBean.id)
//            startWebViewActivity(listBean.url)
        }
    }


    override fun initListener() {

    }

    override fun initData(savedInstanceState: Bundle?) {
        listViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<ListBean>>{
            override fun success(result: ArrayList<ListBean>?) {
                homeNewsListAdapter.setNewData(result)
            }
        })
        listViewModel.requestData()
    }

    /**
     * 启动 WebView 页面
     */
    private fun startWebViewActivity(url : String) {
        startActivity(Intent(activity, WebViewActivity::class.java).putExtra(GKConstant.FLAG_WEBVIEW_URL, url))
    }
}