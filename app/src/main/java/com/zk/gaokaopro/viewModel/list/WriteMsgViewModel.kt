package com.zk.gaokaopro.viewModel.list

import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.ListBean
import com.zk.gaokaopro.viewModel.BaseViewModel
import io.reactivex.Observable

class WriteMsgViewModel : BaseViewModel<ListBean>() {

    var uid: Int = 0
    var title: String = ""
    var author: String = ""
    var summary: String = ""
    var des: String = ""

    override fun getObservable(): Observable<GKBaseBean<ListBean>> {
        return gkApi.requestWriteMsg(uid, author, title, summary, des)
    }
}