package com.zkxl.www.mvvm.model

import com.zkxl.www.mvvm.model.bean.TreeBean
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Create by Panda on 2019/1/15
 */
interface Apis {

    @GET("/project/tree/json")
    fun getTree(): Observable<TreeBean>

}