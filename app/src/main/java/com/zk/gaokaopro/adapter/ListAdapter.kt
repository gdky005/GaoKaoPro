package com.zk.gaokaopro.adapter

import com.blankj.utilcode.util.TimeUtils
import com.zk.gaokaopro.R
import com.zk.gaokaopro.model.ListBean
import team.zhuoke.sdk.component.ZKAdapter
import team.zhuoke.sdk.component.ZKViewHolder

class ListAdapter(data: MutableList<ListBean>?) :
    ZKAdapter<ListBean, ZKViewHolder>(R.layout.item_list, data) {

    override fun convert(helper: ZKViewHolder?, item: ListBean?) {
        if (helper != null && item != null) {
            helper.setText(R.id.tvTitle, item.title)
            helper.setText(R.id.tvTime, TimeUtils.millis2String(item.createTime))
            helper.setText(R.id.tvAuthor, item.author)
            helper.setText(R.id.tvSummery, item.summary)
        }
    }

}