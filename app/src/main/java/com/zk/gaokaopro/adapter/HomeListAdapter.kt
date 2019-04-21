package com.zk.gaokaopro.adapter

import com.bumptech.glide.Glide
import com.zk.gaokaopro.R
import com.zk.gaokaopro.model.HomeListBean
import team.zhuoke.sdk.component.ZKAdapter
import team.zhuoke.sdk.component.ZKViewHolder

class HomeListAdapter(data: MutableList<HomeListBean>?) :
    ZKAdapter<HomeListBean, ZKViewHolder>(R.layout.item_home_list, data) {

    override fun convert(helper: ZKViewHolder?, item: HomeListBean?) {
        if (helper != null && item != null) {
            helper.setText(R.id.tvTitle, item.title)

            Glide.with(mContext).load(item.url).into(helper.getView(R.id.iv))
        }
    }

}