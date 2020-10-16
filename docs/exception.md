LogCat可以打印出日志堆栈信息, 方便追踪调用顺序

```kotlin
try {
    throw UnsupportedOperationException()
} catch (e: Exception) {
    e.logCat() // 输出异常信息
}
```

日志结果

<img src="https://i.imgur.com/H14xCET.png" width="700"/>

每个日志函数都可以输出异常

```kotlin
fun e(stack: Throwable?, tag: String? = this.defaultTag)

fun v(message: String?, tag: String? = this.defaultTag, stack: Throwable? = null)
```

