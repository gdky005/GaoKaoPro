package com.zk.gaokaopro.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.zk.gaokaopro.GKConstant
import com.zk.gaokaopro.GKConstant.images
import com.zk.gaokaopro.R
import com.zk.gaokaopro.activity.WebViewActivity
import com.zk.gaokaopro.adapter.CommendSpacesItemDecoration
import com.zk.gaokaopro.adapter.HomeHListAdapter
import com.zk.gaokaopro.adapter.HomeNewsListAdapter
import com.zk.gaokaopro.model.CategoryBean
import com.zk.gaokaopro.model.HomeListBean
import com.zk.gaokaopro.model.NewsListBean
import com.zk.gaokaopro.model.RecommendBean
import com.zk.gaokaopro.utils.GlideImageLoader
import com.zk.gaokaopro.viewModel.BaseViewModel
import com.zk.gaokaopro.viewModel.home.CategoryViewModel
import com.zk.gaokaopro.viewModel.home.NewsListViewModel
import com.zk.gaokaopro.viewModel.home.RecommendViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import team.zhuoke.sdk.base.BaseFragment

class HomeFragment : BaseFragment() {

   companion object {
       // 图片的间距
       const val picColumnSpace = 10F
   }

    // 推荐图片的列数
    val picColumn = GKConstant.PIC_COLUMN

    private var imageUrls =  mutableListOf<RecommendBean>()


    val homHomeListAdapter = HomeHListAdapter(null)
    val homeNewsListAdapter = HomeNewsListAdapter(null)

    private val recommendViewModel = RecommendViewModel()
    private val categoryViewModel = CategoryViewModel()
    private val newsListViewModel = NewsListViewModel()

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

        // 推荐数据
        val images = mutableListOf(R.drawable.default_pic)
        //设置图片加载器
        banner.setImageLoader(GlideImageLoader())
        banner.setOnBannerListener { position ->
            if (imageUrls.size > 0)
                startWebViewActivity(imageUrls[position].url)
        }
//        //设置图片集合
        banner.setImages(images)
//        //banner设置方法全部调用完毕时最后调用
        banner.start()



        recommendViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<RecommendBean>>{
            override fun success(result: ArrayList<RecommendBean>?) {
                if (result != null) {
                    imageUrls = result

                    val imageUrlList= mutableListOf<String>()
                    for (recommendBean in result) {
                        imageUrlList.add(recommendBean.imgUrl)
                    }

                    //设置图片集合
                    banner.setImages(imageUrlList)
                    //banner设置方法全部调用完毕时最后调用
                    banner.start()
                }
            }
        })
        recommendViewModel.requestData()


        //8大分类
        categoryViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<CategoryBean>> {
            override fun success(result: ArrayList<CategoryBean>?) {
                homHomeListAdapter.setNewData(result)
            }
        })
        categoryViewModel.requestData()


        //新闻列表
        newsListViewModel.setObserveListener(this, this, object : BaseViewModel.SuccessCallBack<ArrayList<NewsListBean>>{
            override fun success(result: ArrayList<NewsListBean>?) {
                homeNewsListAdapter.setNewData(result)
            }
        })
        newsListViewModel.requestData()
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
        homHomeListAdapter.setOnItemClickListener { adapter, view, position ->
            val categoryBean = adapter.data[position] as CategoryBean
            startWebViewActivity(categoryBean.url)
        }

        listRecyclerView.layoutManager = LinearLayoutManager(activity)
        listRecyclerView.adapter = homeNewsListAdapter
        homeNewsListAdapter.setOnItemClickListener { adapter, view, position ->
            val newListBean = adapter.data[position] as NewsListBean
            startWebViewActivity(newListBean.url)
        }
    }

    /**
     * 启动 WebView 页面
     */
    private fun startWebViewActivity(url : String) {
        startActivity(Intent(activity, WebViewActivity::class.java).putExtra(GKConstant.FLAG_WEBVIEW_URL, url))
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