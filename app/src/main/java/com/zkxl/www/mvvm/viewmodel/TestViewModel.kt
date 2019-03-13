package com.zkxl.www.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.zkxl.www.mvvm.model.RetrofitUtils
import com.zkxl.www.mvvm.model.bean.StateBean
import com.zkxl.www.mvvm.model.bean.TreeBean

/**
 * Create by Panda on 2019/1/14
 */
class TestViewModel : ViewModel() {

    lateinit var liveData: MutableLiveData<StateBean<TreeBean>>

    fun getData(): MutableLiveData<StateBean<TreeBean>> {
        liveData = MutableLiveData()

        liveData.postValue(StateBean.loading(null))
        RetrofitUtils.getTree { t ->
            when (t.errorCode) {
                0 -> liveData.postValue(StateBean.success(t))
                else -> liveData.postValue(StateBean.error(t.errorMsg, t))
            }
        }
        return liveData
    }

}