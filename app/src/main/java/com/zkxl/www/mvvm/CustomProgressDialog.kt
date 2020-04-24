package com.zkxl.www.mvvm

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager


/**
 * Create by Panda on 2019/4/4
 */
class CustomProgressDialog : ProgressDialog {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, theme: Int) : super(context, theme) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCancelable(false)
        setCanceledOnTouchOutside(false)

        setContentView(R.layout.load_dialog)
        val params = window!!.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params

    }

}