package com.zk.gaokaopro.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.zk.gaokaopro.R
import com.zk.gaokaopro.activity.MsgDetailActivity
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
            startWebViewActivity(listBean.id)
        }
    }


    override fun initListener() {

        btWriteMsg.setOnClickListener {
            ToastUtils.showShort("写消息")
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        listViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<ListBean>>{
            override fun success(result: ArrayList<ListBean>?) {
                homeNewsListAdapter.setNewData(result)
            }
        })
        listViewModel.requestData()
    }

    private fun startWebViewActivity(id : Int) {
        startActivity(Intent(activity, MsgDetailActivity::class.java).putExtra(MsgDetailActivity.FLAG_MSG_DETAIL_ID, id))
    }
}