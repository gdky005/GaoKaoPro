package com.zk.gaokaopro.activity

import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.zk.gaokaopro.R
import com.zk.gaokaopro.model.ListBean
import com.zk.gaokaopro.viewModel.BaseViewModel
import com.zk.gaokaopro.viewModel.list.MsgDetailViewModel
import kotlinx.android.synthetic.main.layout_msg_detail.*
import team.zhuoke.sdk.base.BaseActivity


class MsgDetailActivity : BaseActivity() {

    companion object {
        var FLAG_MSG_DETAIL_ID = "flag_msg_detail_id"
    }

    private var msgDetailViewModel: MsgDetailViewModel = MsgDetailViewModel()

    override fun initListener() {
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_msg_detail
    }

    override fun initViews() {
        setBarState(rlMsgDetail)
        BarUtils.addMarginTopEqualStatusBarHeight(rlMsgDetail)
    }

    override fun initData() {
        val id = intent.getIntExtra(FLAG_MSG_DETAIL_ID, 0)

        if (id <= 0) {
            ToastUtils.showShort("请传入文章 id 哦！")
            return
        }

        msgDetailViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ListBean>{
            override fun success(result: ListBean?) {
                updateUI(result)
            }
        })
        msgDetailViewModel.id = id
        msgDetailViewModel.requestData()
    }

    private fun updateUI(result: ListBean?) {

        if (result != null) {
            tvDetailTitle.text = result.title
            tvDetailTime.text = TimeUtils.millis2String(result.createTime * 1000)
            tvDetailAuthor.text = result.author
            tvDetailSummary.text = result.summary
            tvDetailContent.text = result.des
        }

    }

}