package com.zkxl.www.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.zkxl.www.mvvm.model.RetrofitUtils
import com.zkxl.www.mvvm.model.bean.Bean
import com.zkxl.www.mvvm.model.bean.TreeBean

/**
 * Create by Panda on 2019/1/14
 */
class TestViewModel : ViewModel() {

    val liveData: MutableLiveData<Bean<List<TreeBean>>> = MutableLiveData()

    fun getData(): MutableLiveData<Bean<List<TreeBean>>> {

        liveData.postValue(Bean.loading(null))
        RetrofitUtils.getTree(
                { t ->
                    when (t.errorCode) {
                        0 -> liveData.postValue(Bean.success(t.data))
                        else -> liveData.postValue(Bean.error(t.errorMsg, t.data))
                    }
                },
                { ex ->
                    liveData.postValue(Bean.error(ex.message!!, null))
                }
        )
        return liveData
    }

}