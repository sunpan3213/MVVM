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

class MainActivity : BaseActivity() {

    lateinit var model: TestViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        model = ViewModelProviders.of(this).get(TestViewModel::class.java)

        val observer = Observer<Bean<List<TreeBean>>> { res ->
            when (res?.errorCode) {
                Bean.SUCCESS -> {
                    finishLoading()
                    tv.text = res.data?.get(0)?.name
                }
                Bean.ERROR -> {
                    finishLoading()
                    Toast.makeText(this, res.errorMsg, Toast.LENGTH_SHORT).show()
                }
                Bean.LOADING -> showLoading()
            }
        }

        model.liveData.observe(this, observer)
    }

    override fun initEvent() {
        bt.setOnClickListener {
            model.getData()
        }
    }


}
