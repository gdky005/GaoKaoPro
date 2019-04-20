package com.zk.gaokaopro.net

import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver

open class BaseHttpObserver<T> : DisposableObserver<T>() {
    override fun onComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNext(@NonNull response: T) {}

    override fun onError(@NonNull e: Throwable) {}
}

