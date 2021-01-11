package com.mx.mlog

import android.text.TextUtils
import android.util.Log

/**
 * author : mx
 * date   : 2020/11/25 11:44
 */
object MLog {
    private var sDebug = BuildConfig.DEBUG
    private var sTag = "TAG"


    //得到上一个栈帧
    private// find the target invoked method
    val targetStackTraceElement: StackTraceElement?
        get() {
            var targetStackTrace: StackTraceElement? = null
            var shouldTrace = false
            val stackTrace = Thread.currentThread().stackTrace
            for (stackTraceElement in stackTrace) {
                val isLogMethod = stackTraceElement.className == MLog::class.java.name
                if (shouldTrace && !isLogMethod) {
                    targetStackTrace = stackTraceElement
                    break
                }
                shouldTrace = isLogMethod
            }
            return targetStackTrace
        }

    fun init(debug: Boolean, tag: String) {
        sDebug = debug
        sTag = tag
    }

    private fun getFinalTag(tag: String): String {
        return if (!TextUtils.isEmpty(tag)) {
            tag
        } else sTag
    }

    fun d(msg: String?) {
        if (!sDebug) return

        val targetStackTraceElement = targetStackTraceElement
        Log.d(
            sTag,
            ".(${targetStackTraceElement!!.fileName}:${targetStackTraceElement.lineNumber})    $msg"
        )
    }

    fun d(tag: String, msg: String?) {
        if (!sDebug) return

        val finalTag = getFinalTag(tag)
        val targetStackTraceElement = targetStackTraceElement
        Log.d(
            finalTag,
            ".(${targetStackTraceElement!!.fileName}:${targetStackTraceElement.lineNumber})    $msg"
        )
    }

    fun e(msg: String?) {
        if (!sDebug) return
        val targetStackTraceElement = targetStackTraceElement
        Log.e(
            sTag,
            ".(${targetStackTraceElement!!.fileName}:${targetStackTraceElement.lineNumber})    $msg"
        )
    }

    fun e(tag: String, msg: String?) {
        if (!sDebug) return

        val finalTag = getFinalTag(tag)
        val targetStackTraceElement = targetStackTraceElement
        Log.e(
            finalTag,
            ".(${targetStackTraceElement!!.fileName}:${targetStackTraceElement.lineNumber})    $msg"
        )
    }

    fun i(msg: String?) {
        if (!sDebug) return
        val targetStackTraceElement = targetStackTraceElement
        Log.i(
            sTag,
            ".(${targetStackTraceElement!!.fileName}:${targetStackTraceElement.lineNumber})    $msg"
        )
    }

    fun i(tag: String, msg: String?) {
        if (!sDebug) return

        val finalTag = getFinalTag(tag)
        val targetStackTraceElement = targetStackTraceElement
        Log.i(
            finalTag,
            ".(${targetStackTraceElement!!.fileName}:${targetStackTraceElement.lineNumber})    $msg"
        )
    }
}