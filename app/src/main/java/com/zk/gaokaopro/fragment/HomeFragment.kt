package com.zk.gaokaopro.fragment

import android.os.Bundle
import android.view.View
import com.zk.gaokaopro.R
import com.zk.gaokaopro.utils.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import team.zhuoke.sdk.base.BaseFragment


class HomeFragment : BaseFragment() {

    override fun initListener() {

    }

    override fun initData(savedInstanceState: Bundle?) {
        val images = mutableListOf(
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3351381896,4018125707&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1218711089,2266838887&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=283299632,427497639&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=136990643,61379176&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1288117223,3597668578&fm=26&gp=0.jpg"
        )

        //设置图片加载器
        banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        banner.setImages(images)
        //banner设置方法全部调用完毕时最后调用
        banner.start()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(rootView: View) {
    }

    override fun onStart() {
        super.onStart()
        //开始轮播
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        //结束轮播
        banner.stopAutoPlay()
    }
}