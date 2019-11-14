package com.drake.logcat.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drake.logcat.LogCat
import com.drake.logcat.LogLevel
import com.drake.logcat.Tree
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        LogCat.addTree(object : Tree {
            override fun log(message: String, tag: String, t: Throwable?, level: LogLevel) {

            }
        })

        btn_json.setOnClickListener {
            LogCat.json(
                getString(R.string.json),
                "JSON日志",
                "https://github.com/liangjingkanji?tab=repositories"
            )
        }

        btn_log.setOnClickListener { LogCat.e("错误", "日志") }
    }
}
