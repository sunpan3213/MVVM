package com.zkxl.www.mvvm.utils

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.zkxl.www.mvvm.R

/**
 * Create by Panda on 2019/4/19
 */
object DialogUtils {

    fun showCommonDialog(context: Context, message: String, btnText: String, sure: () -> Unit, diss: () -> Unit) {
        val dialog = Dialog(context, R.style.ActionSheetDialogStyle)
        dialog.setContentView(R.layout.dialog_common)
        val window = dialog.window
        val params = window?.attributes
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        params?.width = windowManager.defaultDisplay.width/2    //屏幕宽度的一半,可自行修改
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.gravity = Gravity.CENTER    //显示位置
        window?.attributes = params

        val textView = window?.findViewById<TextView>(R.id.message)
        val commit = window?.findViewById<Button>(R.id.yes)
        val cancel = window?.findViewById<Button>(R.id.no)
        textView?.text = message
        commit?.text = btnText

        commit?.setOnClickListener {
            dialog.dismiss()
            sure.invoke()
        }

        cancel?.setOnClickListener {
            dialog.dismiss()
            diss.invoke()
        }

        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    /**
    * 自己去实现具体所需的dialog布局,以及所需的参数
    */
    fun otherDialog(){

    }
}