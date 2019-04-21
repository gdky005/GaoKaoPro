package com.zk.gaokaopro.adapter

import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ScreenUtils
import com.bumptech.glide.Glide
import com.zk.gaokaopro.GKConstant
import com.zk.gaokaopro.R
import com.zk.gaokaopro.fragment.HomeFragment.Companion.picColumnSpace
import com.zk.gaokaopro.model.CategoryBean
import team.zhuoke.sdk.component.ZKAdapter
import team.zhuoke.sdk.component.ZKViewHolder

class HomeHListAdapter(data: MutableList<CategoryBean>?) :
    ZKAdapter<CategoryBean, ZKViewHolder>(R.layout.item_home_h_list, data) {

    // 推荐图片的间距值
    @Volatile
    var picWidth = 0
    lateinit var params: LinearLayout.LayoutParams

    override fun convert(helper: ZKViewHolder?, item: CategoryBean?) {
        if (helper != null && item != null) {
            val imageView = helper.getView<ImageView>(R.id.iv)
            val params = imageView.layoutParams

            if(picWidth == 0) {
                val picColumn = GKConstant.PIC_COLUMN

                // 根据设置的 padding 和 margin 左右 进行计算图片的宽度。每行三个
//                val imageSpace = 2F*2 + 16F*2 + 26F*2 + picColumnSpace/2
                val imageSpace = picColumnSpace
                var width = ScreenUtils.getScreenWidth() - ConvertUtils.dp2px(imageSpace * picColumn)
                width /= picColumn
                picWidth = width

                Log.d("WangQing", "宽度是：$picWidth")
            }

            params.width = picWidth
            params.height = picWidth
            imageView.layoutParams = params

            helper.setText(R.id.tvTitle, item.title)
            Glide.with(mContext).load(item.imgUrl).placeholder(R.drawable.default_pic).into(imageView)
        }
    }

}