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

package com.drake.logcat.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drake.logcat.Chain
import com.drake.logcat.LogCat
import com.drake.logcat.LogInterceptor
import com.drake.logcat.sample.R
import kotlinx.android.synthetic.main.fragment_exception.*

class LogInterceptorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_interceptor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        LogCat.addInterceptor(object : LogInterceptor {
            override fun intercept(chain: Chain) {
                // 拦截器是全局的, 所有日志都会经过于此

                // ... 上次或者保存日志

                if (chain.message == "需要被取消的日志") chain.cancel() // 取消日志的输出
            }
        })

        btn_print.setOnClickListener {
            LogCat.w("需要被取消的日志") // 该日志被拦截器取消
        }
    }
}