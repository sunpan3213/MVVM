package com.zkxl.www.mvvm.model

import com.google.gson.GsonBuilder
import com.zkxl.www.mvvm.BuildConfig
import com.zkxl.www.mvvm.model.bean.Bean
import com.zkxl.www.mvvm.model.bean.TreeBean
import com.zkxl.www.mvvm.utils.LogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * Create by Panda on 2019/1/15
 */
object RetrofitUtils {

    lateinit var apis: Apis

    fun init(): Apis {

        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { msg -> LogUtils.e(msg) })//不重写,部分手机平板需要打开日志
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        builder.addInterceptor(loggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl(
                        if (BuildConfig.DEBUG) {//测试版
                            "http://www.wanandroid.com"
                        } else {//发行版
                            "http://www.wanandroid.com"
                        })
                .client(builder.build())
                .build()
        apis = retrofit.create(Apis::class.java)
        return apis
    }

    fun getTree(observer: (bean: Bean<List<TreeBean>>) -> Unit, error: (t: Throwable) -> Unit): Disposable {
        val disposable = apis.getTree().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
        return disposable
    }
}

