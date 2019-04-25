package com.zkxl.www.mvvm

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zkxl.www.mvvm.viewmodel.TestViewModel

/**
 * Create by Panda on 2019/3/14
 */

abstract class BaseFragment : Fragment() {

    var mContext: Context? = null
    private var dialog: ProgressDialog? = null
    private var mToast: Toast? = null

    private var isViewVisibity: Boolean = false
    private var isViewCreated: Boolean = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(getArguments())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(mContext).inflate(getLayoutId(), container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initEvent()
    }

    /*
   * 适合viewpager+fragment
   */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            isViewVisibity = true
            lasyLoadData()
        } else {
            isViewVisibity = false
            viewInvisible()
        }
    }

    open protected fun init(bundle: Bundle?) {

    }

    abstract fun getLayoutId(): Int

    open protected fun initView() {

    }

    open protected fun initData() {

    }

    open protected fun initEvent() {

    }

    private fun lasyLoadData() {
        if (isViewCreated && isViewVisibity) {
            doLazy()
            isViewCreated = false
            isViewVisibity = false
        }
    }

    /*
    * 适合fragment的show和hide
    */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            hide()
        } else {
            show()
        }
    }

    protected open fun viewInvisible() {}

    protected open fun doLazy() {}

    protected open fun hide() {}

    protected open fun show() {}

    fun showLoading() {
        if (dialog == null) {
            dialog = CustomProgressDialog(mContext!!, R.style.CustomDialog)
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
            mToast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT)
        } else {
            mToast?.setText(str)
        }
        mToast?.show()
    }

    inline fun <reified T> go() {
        startActivity(Intent(mContext, T::class.java))
    }

    inline fun <reified T> go(bundle: Bundle) {
        startActivity(Intent(mContext, T::class.java).putExtras(bundle))
    }

    inline fun <reified T> goForResult(req: Int) {
        startActivityForResult(Intent(mContext, T::class.java), req)
    }

    inline fun <reified T> goForResult(req: Int, bundle: Bundle) {
        startActivityForResult(Intent(mContext, T::class.java).putExtras(bundle), req)
    }
}