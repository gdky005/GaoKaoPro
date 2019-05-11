package com.zk.gaokaopro.viewModel

import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.LoginBean
import io.reactivex.Observable

class RegisterViewModel : BaseViewModel<LoginBean>() {

    var userName = ""
    var password = ""
    var passwordNew = ""

    override fun getObservable(): Observable<GKBaseBean<LoginBean>> {
        return gkApi.requestRegister(userName, password, passwordNew)
    }
}