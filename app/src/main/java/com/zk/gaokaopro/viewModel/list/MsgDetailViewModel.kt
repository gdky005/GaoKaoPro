package com.zk.gaokaopro.viewModel.list

import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.ListBean
import com.zk.gaokaopro.viewModel.BaseViewModel
import io.reactivex.Observable

class MsgDetailViewModel : BaseViewModel<ListBean>() {
    var id: Int = 0
    override fun getObservable(): Observable<GKBaseBean<ListBean>> {
        return gkApi.requestMsgDetail(id)
    }
}