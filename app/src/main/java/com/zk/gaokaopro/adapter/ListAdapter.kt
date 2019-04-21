package com.zk.gaokaopro.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zk.gaokaopro.R
import com.zk.gaokaopro.model.ListBean
import team.zhuoke.sdk.component.ZKAdapter
import team.zhuoke.sdk.component.ZKViewHolder

class ListAdapter(data: MutableList<ListBean>?) :
    ZKAdapter<ListBean, ZKViewHolder>(R.layout.item_list, data) {

    override fun convert(helper: ZKViewHolder?, item: ListBean?) {
        if (helper != null && item != null) {
            val imageView = helper.getView<ImageView>(R.id.iv)
            helper.setText(R.id.tvTitle, item.title)
            Glide.with(mContext).load(item.imgUrl).placeholder(R.drawable.default_pic).into(imageView)
        }
    }

}