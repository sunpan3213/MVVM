package com.zkxl.www.mvvm.model.bean

/**
 * Create by Panda on 2019/3/12
 */

data class StateBean<T>(val code: Int, val data: T?, val msg: String) {

    companion object {
        const val SUCCESS = 0
        const val ERROR = 1
        const val LOADING = 2

        fun <T> success(data: T) = StateBean(SUCCESS, data, "success")

        fun <T> error(msg: String, data: T?) = StateBean(ERROR, data, msg)

        fun <T> loading(data: T?) = StateBean(LOADING, data, "loading")
    }

}