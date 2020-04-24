package com.zkxl.www.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.zkxl.www.mvvm.model.BaseViewModel
import com.zkxl.www.mvvm.model.NetObserver
import com.zkxl.www.mvvm.model.RetrofitUtils
import com.zkxl.www.mvvm.model.bean.TreeBean
import io.reactivex.disposables.Disposable

/**
 * Create by Panda on 2019/1/14
 */
class TestViewModel : BaseViewModel() {

    val liveData: MutableLiveData<List<TreeBean>> = MutableLiveData()

    fun getData(){
        RetrofitUtils.getTree(object : NetObserver<List<TreeBean>>(this) {
            override fun success(data: List<TreeBean>) {
                liveData.postValue(data)
            }
        })
    }

}