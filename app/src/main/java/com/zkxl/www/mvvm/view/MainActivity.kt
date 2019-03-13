package com.zkxl.www.mvvm.view

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.zkxl.www.mvvm.R
import com.zkxl.www.mvvm.model.RetrofitUtils
import com.zkxl.www.mvvm.model.bean.StateBean
import com.zkxl.www.mvvm.model.bean.TreeBean
import com.zkxl.www.mvvm.viewmodel.TestViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var model: TestViewModel
    private var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitUtils.init()

        model = ViewModelProviders.of(this).get(TestViewModel::class.java)

        val observer = Observer<StateBean<TreeBean>> { res ->
            when (res?.code) {
                StateBean.SUCCESS -> {
                    finishLoading()
                    tv.text = res.data?.data?.get(0)?.name
                }
                StateBean.ERROR -> {
                    finishLoading()
                    Toast.makeText(this, res.msg, Toast.LENGTH_SHORT).show()
                }
                StateBean.LOADING -> showLoading()
            }
        }

        model.getData().observe(this, observer)

        bt.setOnClickListener {
            model.liveData.value = StateBean.error("change",null)
        }
    }

    fun showLoading() {
        if (dialog == null) {
            dialog = ProgressDialog(this)
            dialog?.setMessage("loading")
            dialog?.setCanceledOnTouchOutside(false)
        }
        dialog?.show()
    }

    fun finishLoading() {
        dialog?.dismiss()
    }
}
