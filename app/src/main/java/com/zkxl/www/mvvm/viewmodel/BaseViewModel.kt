package com.zkxl.www.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * create by panda
 * 2020/4/24 0024
 */
open class BaseViewModel : ViewModel() {
    val START = 0
    val END = 1

    val stateLiveData: MutableLiveData<Int> = MutableLiveData()

    fun start() {
        stateLiveData.postValue(START)
    }

    fun end() {
        stateLiveData.postValue(END)
    }
}