package com.zkxl.www.mvvm

import android.app.Application
import com.zkxl.www.mvvm.model.RetrofitUtils

/**
 * Create by Panda on 2019/3/14
 */
class MyApp :Application(){

    companion object{
        lateinit var app:Application
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        RetrofitUtils.init()
    }
}