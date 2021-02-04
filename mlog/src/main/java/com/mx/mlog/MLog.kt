package com.mx.mlog

import android.util.Log

/**
 * author : mx
 * date   : 2020/11/25 11:44
 */
object MLog {
    private var sDebug = true
    private var sTag = "TAG"
    private const val sizeSegment = 3 * 1024

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

    fun d(msg: String) {
        if (!sDebug) return
        log(Log.DEBUG, sTag, msg)
    }

    fun d(tag: String, msg: String) {
        if (!sDebug) return
        log(Log.DEBUG, sTag, msg)
    }

    fun e(msg: String) {
        if (!sDebug) return
        log(Log.ERROR, sTag, msg)
    }

    fun e(tag: String, msg: String) {
        if (!sDebug) return
        log(Log.ERROR, tag, msg)
    }

    fun i(msg: String) {
        if (!sDebug) return
        log(Log.INFO, sTag, msg)
    }

    fun i(tag: String, msg: String) {
        if (!sDebug) return
        log(Log.INFO, tag, msg)
    }

    private fun log(level: Int, tag: String, msg: String) {
        val targetStackTrace = targetStackTraceElement
        var message = ".(${targetStackTrace!!.fileName}:${targetStackTrace.lineNumber})   $msg"
        try {
            val length = message.length.toLong()
            if (length <= sizeSegment) { // 长度小于等于限制直接打印
                Log.println(level, tag, message)
            } else {
                while (message.length > sizeSegment) { // 循环分段打印日志
                    val logContent = message.substring(0, sizeSegment)
                    message = message.replace(logContent, "")
                    Log.println(level, tag, logContent)
                }
                Log.println(level, tag, message) // 打印剩余日志
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.println(level, tag, msg)
        }
    }
}