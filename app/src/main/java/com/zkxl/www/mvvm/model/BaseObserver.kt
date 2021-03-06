package com.zkxl.www.mvvm.model

import com.zkxl.www.mvvm.model.bean.Bean
import com.zkxl.www.mvvm.viewmodel.BaseViewModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * create by panda
 * 2020/4/24 0024
 */
abstract class BaseObserver<T>(val vm: BaseViewModel) : Observer<Bean<T>> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        vm.start()
    }


    override fun onError(e: Throwable) {
        try {
            fail(Bean.ERROR, e.message)
        } finally {
            onFinish()
        }
    }

    protected fun fail(code: Int, msg: String?) {
        //todo fail
    }

    fun onFinish() {
        vm.end()
    }

}