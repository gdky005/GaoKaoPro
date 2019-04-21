package com.zk.gaokaopro.adapter

import android.widget.ImageView
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ScreenUtils
import com.bumptech.glide.Glide
import com.zk.gaokaopro.R
import com.zk.gaokaopro.fragment.HomeFragment.Companion.picColumn
import com.zk.gaokaopro.fragment.HomeFragment.Companion.picColumnSpace
import com.zk.gaokaopro.model.HomeListBean
import team.zhuoke.sdk.component.ZKAdapter
import team.zhuoke.sdk.component.ZKViewHolder

class HomeHListAdapter(data: MutableList<HomeListBean>?) :
    ZKAdapter<HomeListBean, ZKViewHolder>(R.layout.item_home_h_list, data) {

    // 推荐图片的间距值
    private var picWidth = 0

    override fun convert(helper: ZKViewHolder?, item: HomeListBean?) {
        if (helper != null && item != null) {

            val imageView = helper.getView<ImageView>(R.id.iv)
            val params = imageView.layoutParams

            if(picWidth == 0) {
                // 根据设置的 padding 和 margin 左右 进行计算图片的宽度。每行三个
//                val imageSpace = 2F*2 + 16F*2 + 26F*2 + picColumnSpace/2
                val imageSpace = picColumnSpace/2
                var width = ScreenUtils.getScreenWidth() - ConvertUtils.dp2px(imageSpace)
                width /= picColumn

                params.width = width
                params.height = width
            }

            imageView.layoutParams = params



            helper.setText(R.id.tvTitle, item.title)
            Glide.with(mContext).load(item.url).into(imageView)
        }
    }

}