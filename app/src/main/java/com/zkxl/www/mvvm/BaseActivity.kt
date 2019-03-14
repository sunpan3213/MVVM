package com.zkxl.www.mvvm

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Create by Panda on 2019/3/14
 */
abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var mContext: Context
    private var dialog: ProgressDialog? = null
    private var mToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        mContext = this

        initView()
        initData()
        initEvent()
    }

    abstract fun getLayoutId(): Int

    open protected fun initView() {

    }

    abstract fun initData()

    open protected fun initEvent() {

    }

    fun showLoading() {
        if (dialog == null) {
            dialog = ProgressDialog(this)
            dialog?.setMessage("loading")
            dialog?.setCanceledOnTouchOutside(false)
        }
        if (dialog != null && !dialog!!.isShowing) {
            dialog?.show()
        }
    }

    fun finishLoading() {
        dialog?.dismiss()
    }

    @SuppressLint("ShowToast")
    fun toast(str: String) {
        if (mToast == null) {
            mToast = Toast.makeText(this, str, Toast.LENGTH_SHORT)
        } else {
            mToast?.setText(str)
        }
        mToast?.show()
    }

    fun goActivity(clz: Class<*>) {
        val intent = Intent(this, clz)
        startActivity(intent)
    }

    fun goActivity(clz: Class<*>, bundle: Bundle) {
        val intent = Intent(this, clz)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun goActivityForResult(clz: Class<*>, req: Int) {
        val intent = Intent(this, clz)
        startActivityForResult(intent, req)
    }

    fun goActivityForResult(clz: Class<*>, req: Int, bundle: Bundle) {
        val intent = Intent(this, clz)
        intent.putExtras(bundle)
        startActivityForResult(intent, req)
    }

}