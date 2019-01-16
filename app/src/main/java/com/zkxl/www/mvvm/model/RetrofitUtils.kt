package com.zkxl.www.mvvm.model

import com.zkxl.www.mvvm.BuildConfig
import com.zkxl.www.mvvm.model.bean.TreeBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create by Panda on 2019/1/15
 */
object RetrofitUtils {

    lateinit var apis: Apis

    fun init(): Apis {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(
                        if (BuildConfig.DEBUG) {//测试版
                            "http://www.wanandroid.com"
                        } else {//发行版
                            "http://www.wanandroid.com"
                        })
                .build()
        apis = retrofit.create(Apis::class.java)
        return apis
    }

    fun getTree(success: (treeBean: TreeBean) -> Unit, error: (t: Throwable) -> Unit):Disposable {
        val disposable = apis.getTree().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, error)
        return disposable
    }
}