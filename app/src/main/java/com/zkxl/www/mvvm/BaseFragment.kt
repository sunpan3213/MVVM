package com.zkxl.www.mvvm

import android.annotation.SuppressLint
import android.app.Fragment
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(mContext).inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initEvent()
    }

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

    open protected fun init(bundle: Bundle) {

    }

    abstract fun getLayoutId(): Int

    open protected fun initView() {

    }

    abstract protected fun initData()

    open protected fun initEvent() {

    }

    private fun lasyLoadData() {
        if (isViewCreated && isViewVisibity) {
            doLazy()
            isViewCreated = false
            isViewVisibity = false
        }
    }

    protected open fun viewInvisible() {}

    protected open fun doLazy() {}

    fun showLoading() {
        if (dialog == null) {
            dialog = ProgressDialog(mContext)
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
            mToast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT)
        } else {
            mToast?.setText(str)
        }
        mToast?.show()
    }
}