package com.zkxl.www.mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.widget.Toast
import com.zkxl.www.mvvm.BaseActivity
import com.zkxl.www.mvvm.R
import com.zkxl.www.mvvm.model.bean.Bean
import com.zkxl.www.mvvm.model.bean.TreeBean
import com.zkxl.www.mvvm.viewmodel.TestViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : BaseActivity() {

    lateinit var model: TestViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun initData() {
        model = ViewModelProviders.of(this).get(TestViewModel::class.java)

        model.liveData.observe(this, Observer<List<TreeBean>> { data ->
            tv.text = data!![Random.nextInt(data.size)].name
        })
        model.stateLiveData.observe(this, Observer<Int> { t ->
            when (t) {
                model.START -> showLoading()
                model.END   -> finishLoading()
            }
        })
    }

    override fun initEvent() {
        bt.setOnClickListener {
            model.getData()
        }
    }


}
