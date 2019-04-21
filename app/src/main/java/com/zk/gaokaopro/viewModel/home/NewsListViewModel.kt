package com.zk.gaokaopro.viewModel.home

import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.NewsListBean
import com.zk.gaokaopro.viewModel.BaseViewModel
import io.reactivex.Observable

class NewsListViewModel : BaseViewModel<ArrayList<NewsListBean>>() {
    override fun getObservable(): Observable<GKBaseBean<ArrayList<NewsListBean>>> {
        return gkApi.requestNewList()
    }
}