package com.zkxl.www.mvvm.model

import com.zkxl.www.mvvm.model.bean.Bean

/**
 * create by panda
 * 2020/4/24 0024
 */
abstract class NetObserver<T>(vm: BaseViewModel) : BaseObserver<T>(vm) {

    abstract fun success(data: T)

    override fun onNext(t: Bean<T>) {
        try {
            if (0 == t.errorCode) {
                success(t.data!!)
            } else {
                fail(t.errorCode, t.errorMsg)
            }
        } finally {
            onFinish()
        }
    }

}