package com.zk.gaokaopro.net

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ObserveOnMainCallAdapterFactory private constructor() : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (CallAdapter.Factory.getRawType(returnType) != Observable::class.java) {
            return null // Ignore non-Observable types.
        }

        // Look up the next call adapter which would otherwise be used if this one was not present.

        val delegate = retrofit.nextCallAdapter(
            this, returnType,
            annotations
        ) as CallAdapter<Any, Observable<*>>

        return object : CallAdapter<Any, Any> {
            override fun adapt(call: Call<Any>): Any {
                // Delegate to get the normal Observable...
                val o = delegate.adapt(call)
                // ...and change it to send notifications to the observer on the specified scheduler.
                return o.observeOn(AndroidSchedulers.mainThread())
            }

            override fun responseType(): Type {
                return delegate.responseType()
            }
        }
    }

    companion object {

        fun create(): ObserveOnMainCallAdapterFactory {
            return ObserveOnMainCallAdapterFactory()
        }
    }
}
