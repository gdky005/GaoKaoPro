package com.zk.gaokaopro.activity

import com.blankj.utilcode.util.BarUtils
import com.zk.gaokaopro.R
import kotlinx.android.synthetic.main.layout_write_msg.*
import team.zhuoke.sdk.base.BaseActivity


class WriteMsgActivity : BaseActivity() {

    override fun initListener() {
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_write_msg
    }

    override fun initViews() {
        setBarState(rlWriteMsg)
        BarUtils.addMarginTopEqualStatusBarHeight(rlWriteMsg)
    }

    override fun initData() {
    }


}