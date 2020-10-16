日志拦截器可以取消日志输出和改变日志内容, 一般应用场景是保存日志到本地或者上传至服务器

```kotlin
LogCat.addInterceptor(object : LogInterceptor {
    override fun intercept(chain: Chain) {
        // 拦截器是全局的, 所有日志都会经过于此

        // ... 上次或者保存日志

        if (chain.message == "需要被取消的日志") chain.cancel() // 取消日志的输出
    }
})
```

初始化的时候也可以添加拦截器

```kotlin hl_lines="4"
LogCat.config {
    defaultTag = "默认日志"
    enabled = BuildConfig.DEBUG
    addInterceptor(LogStoreInterceptor())
}
```

## Chain

所有日志拦截器都包含一个`Chain`对象, 其中包含所有日志信息

| 函数 | 描述 |
|-|-|
| level | 日志等级 |
| message | 日志消息 |
| tag | 日志标签 |
| stack | 日志堆栈异常 |
| cancel | 终止日志输出 |

> Chain.cancel()函数可以终止日志的输出(即终止输出到AndroidStudio的`LogCat`)

## 自定义日志等级

这里演示自定义一个上传到服务器的日志

```kotlin
val LOG_UPLOAD = 8

LogCat.addInterceptor(object : LogInterceptor {
    override fun intercept(chain: Chain) {
        if (chain.level == LOG_UPLOAD) {
        // ... 上传日志到服务器
        }
    }
})
```

使用日志

```kotlin
LogCat.print(LOG_UPLOAD, "错误日志")
```
