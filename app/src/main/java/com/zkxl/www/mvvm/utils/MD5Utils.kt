package com.zkxl.www.mvvm.utils

import android.support.annotation.IntegerRes
import java.lang.StringBuilder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Create by Panda on 2019-04-25
 */
object MD5Utils {

    fun encrypt(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(str.toByteArray())
        val bytes = md.digest()
        val sb = StringBuilder()
        for (offset in bytes) {
            var i = offset.toInt()
            if (i < 0) i += 256
            if (i < 16) sb.append("0")
            sb.append(i.toString(16))
        }
        return sb.toString().toUpperCase()
    }

}