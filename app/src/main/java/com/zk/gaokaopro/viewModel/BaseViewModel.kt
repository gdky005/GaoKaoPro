package com.zk.gaokaopro.viewModel

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.blankj.utilcode.util.ToastUtils
import com.zk.gaokaopro.GKConstant
import com.zk.gaokaopro.api.GKApi
import com.zk.gaokaopro.manager.NetManager
import com.zk.gaokaopro.model.GKBaseBean
import com.zk.gaokaopro.subscriber.GKHttpSubscriber
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel<T> : ViewModel() {

    companion object {
        const val TAG = "BaseViewModel"
    }

    var gkApi: GKApi = NetManager.instance.getGKApi()
    val liveData = MutableLiveData<GKBaseBean<T>>()
    private var viewModel: BaseViewModel<T>? = null

    abstract fun getObservable(): Observable<GKBaseBean<T>>

    var successCallBack : SuccessCallBack<T>? = null

    @SuppressLint("CheckResult")
    private fun request(observable : Observable<GKBaseBean<T>>) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : GKHttpSubscriber<GKBaseBean<T>>() {
                override fun onSuccess(t: GKBaseBean<T>) {
                    if (viewModel != null) {
                        viewModel!!.liveData.value = GKBaseBean.success(t.result)
                    }
                }

                override fun onOtherError(e: Throwable) {
                    if (viewModel != null) {
                        viewModel!!.liveData.value = GKBaseBean.otherError(null)
                    }
                }

                override fun onResponseError(errorCode: Int, message: String) {
                    if (viewModel != null) {
                        viewModel!!.liveData.value = GKBaseBean.error(errorCode, message, null)
                    }
                }

            })
    }

    fun requestData() {
        return request(getObservable())
    }

    open fun setObserveListener(fragmentActivity: FragmentActivity, owner: LifecycleOwner, callBack: SuccessCallBack<T>) {
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(fragmentActivity).get(javaClass)
            observerData(callBack, owner)
        }
    }

    open fun setObserveListener(fragment: Fragment, owner: LifecycleOwner, callBack: SuccessCallBack<T>) {
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(fragment).get(javaClass)
            observerData(callBack, owner)
        }
    }

    private fun observerData(callBack: SuccessCallBack<T>, owner: LifecycleOwner) {
        successCallBack = callBack
        viewModel!!.liveData.observe(owner, Observer {
            if (it != null) {
                val errorCode = it.code
                if (errorCode == GKConstant.CODE_SUCCESS) {
                    callData(it.result)
    //                            LiveDataBus.instance.getChannel(EventConstants.CONSULTATION_VIEW_COUNT_EVENT, RefreshData::class.java)
    //                                .postValue(refreshData)
                    return@Observer
                }
            }

            val msg = it.msg
            if (!TextUtils.isEmpty(msg))
                ToastUtils.showShort(msg)
        })
    }

    private fun callData(result: T?) {
        if (successCallBack != null) {
            successCallBack!!.success(result)
        }
    }

    private fun logD(msg: String) {
        Log.d(TAG, msg)
    }

    interface SuccessCallBack<T> {
        fun success(result: T?)
    }

}