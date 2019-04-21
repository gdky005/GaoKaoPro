package com.zk.gaokaopro.viewModel

import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.model.LoginBean
import io.reactivex.Observable

class LoginViewModel : BaseViewModel<LoginBean>() {
    override fun getObservable(): Observable<GKBaseBean<LoginBean>> {
        return gkApi.requestLogin()
    }
}