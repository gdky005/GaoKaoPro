package com.zk.gaokaopro.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.zk.gaokaopro.GKConstant
import com.zk.gaokaopro.R
import com.zk.gaokaopro.adapter.CommendSpacesItemDecoration
import com.zk.gaokaopro.adapter.HomeHListAdapter
import com.zk.gaokaopro.adapter.HomeListAdapter
import com.zk.gaokaopro.model.CategoryBean
import com.zk.gaokaopro.model.HomeListBean
import com.zk.gaokaopro.model.RecommendBean
import com.zk.gaokaopro.utils.GlideImageLoader
import com.zk.gaokaopro.viewModel.BaseViewModel
import com.zk.gaokaopro.viewModel.CategoryViewModel
import com.zk.gaokaopro.viewModel.RecommendViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import team.zhuoke.sdk.base.BaseFragment






class HomeFragment : BaseFragment() {

   companion object {
       // 图片的间距
       const val picColumnSpace = 10F
   }

    // 推荐图片的列数
    val picColumn = GKConstant.PIC_COLUMN

    val images = mutableListOf(
        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3351381896,4018125707&fm=26&gp=0.jpg",
        "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1218711089,2266838887&fm=26&gp=0.jpg",
        "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=283299632,427497639&fm=26&gp=0.jpg",
        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=136990643,61379176&fm=26&gp=0.jpg",
        "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1288117223,3597668578&fm=26&gp=0.jpg"
    )

    val homHomeListAdapter = HomeHListAdapter(null)

    private val recommendViewModel = RecommendViewModel()
    private val categoryViewModel = CategoryViewModel()

    override fun getLayoutId(): Int {
        return com.zk.gaokaopro.R.layout.fragment_home
    }

    override fun initView(rootView: View) {
//        BarUtils.addMarginTopEqualStatusBarHeight(homeRootView)
    }

    override fun initListener() {

    }

    override fun initData(savedInstanceState: Bundle?) {
        initRecyclerView()

        val images = mutableListOf(R.drawable.default_pic)

        //设置图片加载器
        banner.setImageLoader(GlideImageLoader())
//        //设置图片集合
        banner.setImages(images)
//        //banner设置方法全部调用完毕时最后调用
        banner.start()

        recommendViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<RecommendBean>>{
            override fun success(result: ArrayList<RecommendBean>?) {

                if (result != null) {
                    var imageUrlList= mutableListOf<String>()
                    for (recommendBean in result) {
                        imageUrlList.add(recommendBean.imgUrl)
//                        imageUrlList.add(images[0])
                    }

                    //设置图片集合
                    banner.setImages(imageUrlList)
                    //banner设置方法全部调用完毕时最后调用
                    banner.start()
                }
            }
        })
        recommendViewModel.requestData()


        categoryViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<CategoryBean>> {
            override fun success(result: ArrayList<CategoryBean>?) {

                if (result != null) {
                    var imageUrlList= mutableListOf<String>()
                    for (recommendBean in result) {
                        imageUrlList.add(recommendBean.imgUrl)
                    }

                    homHomeListAdapter.setNewData(result)
                }
            }

        })
        categoryViewModel.requestData()
    }

    private fun initRecyclerView() {
        //        https://www.jianshu.com/p/b343fcff51b0
        val list = ArrayList<HomeListBean>()
        for (i in 0..7) {
            list.add(HomeListBean("名字：$i", images[i % images.size]))
        }

        val authorPicLLM = GridLayoutManager(activity, picColumn)
        hRecyclerView.addItemDecoration(CommendSpacesItemDecoration(ConvertUtils.dp2px(picColumnSpace)))
        hRecyclerView.layoutManager = authorPicLLM
        hRecyclerView.adapter = homHomeListAdapter


        listRecyclerView.layoutManager = LinearLayoutManager(activity)
        listRecyclerView.adapter = HomeListAdapter(list)
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