package com.zk.gaokaopro.activity

import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.zk.gaokaopro.R
import com.zk.gaokaopro.manager.UserInfoManager
import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.ListBean
import com.zk.gaokaopro.viewModel.BaseViewModel
import com.zk.gaokaopro.viewModel.list.WriteMsgViewModel
import kotlinx.android.synthetic.main.layout_write_msg.*
import team.zhuoke.sdk.base.BaseActivity


class WriteMsgActivity : BaseActivity() {

    var writeMsgViewModel : WriteMsgViewModel = WriteMsgViewModel()

    override fun getLayoutId(): Int {
        return R.layout.layout_write_msg
    }

    override fun initViews() {
        setBarState(rlWriteMsg)
        BarUtils.addMarginTopEqualStatusBarHeight(rlWriteMsg)
    }

    override fun initData() {
        writeMsgViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ListBean>{
            override fun success(gkBaseBean: GKBaseBean<ListBean>, result: ListBean?) {
                ToastUtils.showShort("提交成功")
                finish()
            }
        })
    }

    override fun initListener() {
        btSubmit.setOnClickListener {
            writeMsgViewModel.uid = UserInfoManager.instance.getUserUid()!!
            writeMsgViewModel.author = UserInfoManager.instance.getUserName()!!
            writeMsgViewModel.title = etTitle.text.toString()
            writeMsgViewModel.summary = etSummary.text.toString()
            writeMsgViewModel.des = etContent.text.toString()

            writeMsgViewModel.requestData()
        }
    }

}