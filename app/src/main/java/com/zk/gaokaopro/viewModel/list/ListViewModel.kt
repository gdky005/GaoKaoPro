package com.zk.gaokaopro.viewModel.list

import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.ListBean
import com.zk.gaokaopro.viewModel.BaseViewModel
import io.reactivex.Observable

class ListViewModel : BaseViewModel<ArrayList<ListBean>>() {
    override fun getObservable(): Observable<GKBaseBean<ArrayList<ListBean>>> {
        return gkApi.requestList()
    }
}