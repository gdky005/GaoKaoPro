package com.zk.gaokaopro.viewModel.home

import com.zk.gaokaopro.model.CategoryBean
import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.viewModel.BaseViewModel
import io.reactivex.Observable

class CategoryViewModel : BaseViewModel<ArrayList<CategoryBean>>() {
    override fun getObservable(): Observable<GKBaseBean<ArrayList<CategoryBean>>> {
        return gkApi.requestCategory()
    }
}