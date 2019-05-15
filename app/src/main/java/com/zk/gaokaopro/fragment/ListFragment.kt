package com.zk.gaokaopro.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.BarUtils
import com.zk.gaokaopro.R
import com.zk.gaokaopro.activity.LoginActivity
import com.zk.gaokaopro.activity.MsgDetailActivity
import com.zk.gaokaopro.activity.WriteMsgActivity
import com.zk.gaokaopro.adapter.ListAdapter
import com.zk.gaokaopro.manager.UserInfoManager
import com.zk.gaokaopro.model.GKBaseBean
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
            startWebViewActivity(listBean.id)
        }
        homeNewsListAdapter.setOnLoadMoreListener({
            isFirstLoad = false
            requestMsgData()
        }, listZKRecyclerView)
    }


    override fun initListener() {
        btWriteMsg.setOnClickListener {
            if (UserInfoManager.instance.isLogin()) {
                startActivity(Intent(activity, WriteMsgActivity::class.java))
            } else {
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        listViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<ListBean>>{
            override fun success(gkBaseBean: GKBaseBean<ArrayList<ListBean>>, result: ArrayList<ListBean>?) {

                val count = gkBaseBean.total
                if (homeNewsListAdapter.data.size >= count) {
                    return
                }

                if (isFirstLoad) {
                    homeNewsListAdapter.setNewData(result)
                } else {
                    homeNewsListAdapter.addData(result!!)
                    homeNewsListAdapter.loadMoreComplete()
                }

                if (homeNewsListAdapter.data.size >= count) {
                    homeNewsListAdapter.loadMoreEnd()
                }

            }
        })
//        requestMsgData()
    }

    override fun onResume() {
        super.onResume()
        requestMsgData()
    }

    private var isFirstLoad = true
    private fun requestMsgData() {
        if (!isFirstLoad) {
            listViewModel.page = ++listViewModel.page
        }

        listViewModel.requestData()
    }

    private fun startWebViewActivity(id : Int) {
        startActivity(Intent(activity, MsgDetailActivity::class.java).putExtra(MsgDetailActivity.FLAG_MSG_DETAIL_ID, id))
    }
}