/*
 * Copyright (C) 2018 Drake, Inc.
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
 */
@file:Suppress("SENSELESS_COMPARISON")

package com.drake.logcat

import android.util.Log
import com.drake.logcat.LogCat.defaultTag
import com.drake.logcat.LogCat.enabled
import com.drake.logcat.LogCat.logInterceptors
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import kotlin.math.min


@Suppress("MemberVisibilityCanBePrivate")
/**
 * @property defaultTag 默认日志标签
 * @property enabled 日志全局开关
 * @property logInterceptors 日志拦截器
 */
object LogCat {

    // 日志等级
    const val VERBOSE = 2
    const val DEBUG = 3
    const val INFO = 4
    const val WARN = 5
    const val ERROR = 6
    const val ASSERT = 7

    var defaultTag = "日志"
    var enabled = true

    val logInterceptors by lazy { ArrayList<LogInterceptor>() }

    /**
     * 配置
     */
    fun config(block: LogCat.() -> Unit) {
        block.invoke(this)
    }

    //<editor-fold desc="拦截器">
    /**
     * 添加日志拦截器
     */
    fun addInterceptor(interceptor: LogInterceptor) {
        logInterceptors.add(interceptor)
    }

    /**
     * 删除日志拦截器
     */
    fun removeInterceptor(interceptor: LogInterceptor) {
        logInterceptors.remove(interceptor)
    }

    //</editor-fold>

    // <editor-fold desc="输出">

    fun v(message: String?, tag: String? = this.defaultTag, stack: Throwable? = null) {
        print(VERBOSE, message, tag, stack)
    }

    fun i(message: String?, tag: String? = this.defaultTag, stack: Throwable? = null) {
        print(INFO, message, tag, stack)
    }

    fun d(message: String?, tag: String? = this.defaultTag, stack: Throwable? = null) {
        print(DEBUG, message, tag, stack)
    }

    fun w(message: String?, tag: String? = this.defaultTag, stack: Throwable? = null) {
        print(WARN, message, tag, stack)
    }

    fun e(message: String?, tag: String? = this.defaultTag, stack: Throwable? = null) {
        print(ERROR, message, tag, stack)
    }

    fun wtf(message: String?, tag: String? = this.defaultTag, stack: Throwable? = null) {
        print(ASSERT, message, tag, stack)
    }

    fun e(stack: Throwable?, tag: String? = this.defaultTag) {
        print(ERROR, null, tag, stack)
    }

    /**
     * 输出日志
     * 如果[message]和[stack]为空或者[tag]为空将不会输出日志, 拦截器
     *
     * @param level 日志等级
     * @param message 日志信息
     * @param tag 日志标签
     * @param stack 日志异常
     */
    fun print(
        level: Int = INFO,
        message: String? = null,
        tag: String? = defaultTag,
        stack: Throwable? = null
    ) {
        if (!enabled || tag.isNullOrEmpty()) return

        val chain = Chain(level, message, tag, stack)

        logInterceptors.forEach {
            it.intercept(chain)
        }

        if (chain.cancel || tag.isNullOrEmpty()) return

        val adjustMsg = if (stack == null) {
            if (message.isNullOrEmpty()) " " else message
        } else {
            if (message.isNullOrBlank()) {
                Log.getStackTraceString(stack)
            } else {
                "$message\n${Log.getStackTraceString(stack)}"
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
                    log(level, substring, tag)
                    startIndex += max
                    endIndex += max
                }
            }
        } else {
            log(level, adjustMsg, tag)
        }
    }

    /**
     * Json格式输出Log
     *
     * @param message JSON
     * @param tag     标签
     * @param url     地址
     */
    fun json(
        message: String?,
        tag: String? = this.defaultTag,
        url: String? = null,
        level: Int = INFO
    ) {
        if (!enabled || tag.isNullOrBlank()) return

        val chain = Chain(level, message, tag)

        logInterceptors.forEach {
            it.intercept(chain)
        }

        if (chain.cancel || tag.isNullOrBlank()) return

        if (message.isNullOrBlank()) {
            val adjustMsg = if (url.isNullOrBlank()) message else url
            print(level, adjustMsg, tag)
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

        print(level, finalMsg, tag)
    }

    private fun log(level: Int, adjustMsg: String, tag: String) {
        when (level) {
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
            ASSERT -> {
                Log.wtf(tag, adjustMsg)
            }
            else -> {
                Log.e(tag, adjustMsg)
            }
        }
    }
    // </editor-fold>
}