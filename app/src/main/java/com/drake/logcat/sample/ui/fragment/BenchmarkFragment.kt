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

package com.drake.logcat.sample.ui.fragment

import android.view.View
import com.drake.engine.base.EngineFragment
import com.drake.logcat.LogCat
import com.drake.logcat.sample.R
import com.drake.logcat.sample.databinding.FragmentBenchmarkBinding
import java.util.concurrent.atomic.AtomicBoolean

class BenchmarkFragment : EngineFragment<FragmentBenchmarkBinding>(R.layout.fragment_benchmark) {

    private val isRunning = AtomicBoolean(false)
    override fun initView() {
        binding.btnStart.setOnClickListener {
            if (isRunning.get()) {
                isRunning.set(false)
                binding.btnStart.text = "压测开始"
                binding.pb.visibility = View.GONE
            } else {
                isRunning.set(true)
                startThreads(10, 100)
                startThreads(10000, 100000)
                binding.btnStart.text = "压测停止"
                binding.pb.visibility = View.VISIBLE
            }
        }
    }

    private fun startThreads(mini: Int, maxi: Int) {
        Thread {
            while (isRunning.get()) {
                val length = (mini..maxi).random()
                val msg = generateRandomChineseString(length)
                if (length > 3800) {
                    LogCat.e(msg, tag = "长" + (length % 100).toString())
                } else {
                    LogCat.e(msg, tag = "短" + (length % 100).toString())
                }
            }
        }.start()
    }
    override fun initData() {
    }

    // 生成指定长度的随机中文字符串
    private fun generateRandomChineseString(length: Int): String {
        val chineseUnicodeRange = '\u4e00'..'\u9fff'
        val stringBuilder = StringBuilder()
        repeat(length) {
            val randomChar = chineseUnicodeRange.random()
            stringBuilder.append(randomChar)
        }
        return stringBuilder.toString()
    }
}