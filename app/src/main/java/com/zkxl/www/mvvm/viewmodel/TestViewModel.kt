package com.zkxl.www.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.zkxl.www.mvvm.model.RetrofitUtils

/**
 * Create by Panda on 2019/1/14
 */
class TestViewModel : ViewModel() {

    val liveData: MutableLiveData<String> by lazy {
        val data = MutableLiveData<String>()
        RetrofitUtils.getTree({ success->data.value=success.data[0].name},{ throwable->data.value = throwable.message})
        data
    }

}