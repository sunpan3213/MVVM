package com.zkxl.www.mvvm.utils

import android.util.Log
import com.zkxl.www.mvvm.BuildConfig

/**
 * Create by Panda on 2019/3/14
 */

object LogUtils {

    private val TAG: String = "panda"

    fun v(str: String) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, str)
        }
    }

    fun d(str: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, str)
        }
    }

    fun i(str: String) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, str)
        }
    }

    fun w(str: String) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, str)
        }
    }

    fun e(str: String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, str)
        }
    }
}