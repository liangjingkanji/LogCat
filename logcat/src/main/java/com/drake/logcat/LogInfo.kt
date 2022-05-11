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

package com.drake.logcat

/**
 * @param type 等级
 * @param msg 消息, 如果消息为null会终止Hook拦截, 并且不会输出日志
 * @param  tag 标签
 * @param tr 异常堆栈
 * @param occurred 发生位置
 */
data class LogInfo(
    var type: LogCat.Type,
    var msg: String?,
    var tag: String,
    var tr: Throwable?,
    var occurred: Throwable? = null,
)