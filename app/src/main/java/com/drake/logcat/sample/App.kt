package com.drake.logcat.sample

import android.app.Application
import com.drake.logcat.LogCat

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        LogCat.setConfig("日志", true)
    }
}