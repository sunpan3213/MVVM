package com.zkxl.www.mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zkxl.www.mvvm.R
import com.zkxl.www.mvvm.model.RetrofitUtils
import com.zkxl.www.mvvm.viewmodel.TestViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var model: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitUtils.init()

        model = ViewModelProviders.of(this).get(TestViewModel::class.java)

        val observer = Observer<String> { name -> tv.text = name }

        model.liveData.observe(this, observer)

        bt.setOnClickListener {
            model.liveData.setValue("panda")
        }
    }
}
