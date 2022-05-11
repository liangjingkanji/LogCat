/*
 * Copyright (C) 2018 Drake, https://github.com/liangjingkanji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
@file:Suppress("SENSELESS_COMPARISON")

package com.drake.logcat

import android.util.Log
import com.drake.logcat.LogCat.Type.*
import com.drake.logcat.LogCat.defaultTag
import com.drake.logcat.LogCat.enabled
import com.drake.logcat.LogCat.logHooks
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import kotlin.math.min


@Suppress("MemberVisibilityCanBePrivate")
/**
 * @property defaultTag 默认日志标签
 * @property enabled 日志全局开关
 * @property logHooks 日志拦截器
 */
object LogCat {

    enum class Type {
        VERBOSE, DEBUG, INFO, WARN, ERROR, WTF
    }

    /** 日志默认标签 */
    var defaultTag = "日志"

    /** 是否启用日志 */
    var enabled = true

    /** 日志是否显示代码位置 */
    var traceEnabled = true

    /** 日志的Hook钩子 */
    val logHooks by lazy { ArrayList<LogHook>() }

    /**
     * @param enabled 是否启用日志
     * @param tag 日志默认标签
     */
    fun setDebug(enabled: Boolean, tag: String = defaultTag) {
        this.enabled = enabled
        this.defaultTag = tag
    }

    //<editor-fold desc="Hook">
    /**
     * 添加日志拦截器
     */
    fun addHook(hook: LogHook) {
        logHooks.add(hook)
    }

    /**
     * 删除日志拦截器
     */
    fun removeHook(hook: LogHook) {
        logHooks.remove(hook)
    }
    //</editor-fold>

    // <editor-fold desc="Log">

    @JvmOverloads
    @JvmStatic
    fun v(message: String?, tag: String? = this.defaultTag, trace: Throwable? = Throwable()) {
        print(VERBOSE, message, tag, trace)
    }

    @JvmOverloads
    @JvmStatic
    fun i(message: String?, tag: String? = this.defaultTag, trace: Throwable? = Throwable()) {
        print(INFO, message, tag, trace)
    }

    @JvmOverloads
    @JvmStatic
    fun d(message: String?, tag: String? = this.defaultTag, trace: Throwable? = Throwable()) {
        print(DEBUG, message, tag, trace)
    }

    @JvmOverloads
    @JvmStatic
    fun w(message: String?, tag: String? = this.defaultTag, trace: Throwable? = Throwable()) {
        print(WARN, message, tag, trace)
    }

    @JvmOverloads
    @JvmStatic
    fun e(message: String?, tag: String? = this.defaultTag, trace: Throwable? = Throwable()) {
        print(ERROR, message, tag, trace)
    }

    @JvmOverloads
    @JvmStatic
    fun e(exception: Throwable?, tag: String? = this.defaultTag) {
        exception ?: return
        print(ERROR, exception.stackTraceToString(), tag, null)
    }

    @JvmOverloads
    @JvmStatic
    fun wtf(message: String?, tag: String? = this.defaultTag, trace: Throwable? = Throwable()) {
        print(Type.WTF, message, tag, trace)
    }

    /**
     * 输出日志
     * 如果[message]和[trace]为空或者[tag]为空将不会输出日志, 拦截器
     *
     * @param type 日志等级
     * @param message 日志信息
     * @param tag 日志标签
     * @param trace 日志异常
     */
    @JvmOverloads
    @JvmStatic
    fun print(
        type: Type = INFO,
        message: String? = null,
        tag: String? = defaultTag,
        trace: Throwable? = Throwable()
    ) {
        if (!enabled || tag.isNullOrEmpty()) return

        val info = LogInfo(type, message, tag, trace)
        for (logHook in logHooks) {
            logHook.hook(info)
            if (info.message == null) return
        }

        var adjustMsg = if (message.isNullOrEmpty()) " " else message
        if (traceEnabled && trace != null) {
            trace.stackTrace.getOrNull(1)?.run {
                adjustMsg += " ($fileName:$lineNumber)"
            }
        }

        val max = 3800
        val length = adjustMsg.length
        if (length > max) {
            synchronized(this) {
                var startIndex = 0
                var endIndex = max
                while (startIndex < length) {
                    endIndex = min(length, endIndex)
                    val substring = adjustMsg.substring(startIndex, endIndex)
                    log(type, substring, tag)
                    startIndex += max
                    endIndex += max
                }
            }
        } else {
            log(type, adjustMsg, tag)
        }
    }

    /**
     * Json格式输出Log
     *
     * @param message JSON
     * @param tag     标签
     * @param url     地址
     */
    @JvmOverloads
    @JvmStatic
    fun json(
        message: String?,
        tag: String? = this.defaultTag,
        url: String? = null,
        type: Type = INFO
    ) {
        if (!enabled || tag.isNullOrBlank()) return

        if (message.isNullOrBlank()) {
            val adjustMsg = if (url.isNullOrBlank()) message else url
            print(type, adjustMsg, tag)
            return
        }

        val tokener = JSONTokener(message)
        val obj = try {
            tokener.nextValue()
        } catch (e: Exception) {
            "Parse json error"
        }

        var finalMsg = when (obj) {
            is JSONObject -> {
                obj.toString(2)
            }
            is JSONArray -> {
                obj.toString(2)
            }
            else -> obj.toString()
        }

        if (!url.isNullOrBlank()) finalMsg = "$url\n$finalMsg"

        print(type, finalMsg, tag)
    }

    private fun log(type: Type, adjustMsg: String, tag: String) {
        when (type) {
            VERBOSE -> {
                Log.v(tag, adjustMsg)
            }
            DEBUG -> {
                Log.d(tag, adjustMsg)
            }
            INFO -> {
                Log.i(tag, adjustMsg)
            }
            WARN -> {
                Log.w(tag, adjustMsg)
            }
            ERROR -> {
                Log.e(tag, adjustMsg)
            }
            Type.WTF -> {
                Log.wtf(tag, adjustMsg)
            }
        }
    }
    // </editor-fold>
}