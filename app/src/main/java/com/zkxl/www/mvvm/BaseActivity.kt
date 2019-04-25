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

    open protected fun initData() {

    }

    open protected fun initEvent() {

    }

    fun showLoading() {
        if (dialog == null) {
            dialog = CustomProgressDialog(this, R.style.CustomDialog)
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

    inline fun <reified T> go() {
        startActivity(Intent(this, T::class.java))
    }

    inline fun <reified T> go(bundle: Bundle) {
        startActivity(Intent(this, T::class.java).putExtras(bundle))
    }

    inline fun <reified T> goForResult(req: Int) {
        startActivityForResult(Intent(this, T::class.java), req)
    }

    inline fun <reified T> goForResult(req: Int, bundle: Bundle) {
        startActivityForResult(Intent(this, T::class.java).putExtras(bundle), req)
    }

}