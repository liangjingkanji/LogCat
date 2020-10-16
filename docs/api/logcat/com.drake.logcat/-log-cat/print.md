[logcat](../../index.md) / [com.drake.logcat](../index.md) / [LogCat](index.md) / [print](./print.md)

# print

`fun print(level: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = INFO, message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = defaultTag, stack: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`? = null): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

输出日志
如果[message](print.md#com.drake.logcat.LogCat$print(kotlin.Int, kotlin.String, kotlin.String, kotlin.Throwable)/message)和[stack](print.md#com.drake.logcat.LogCat$print(kotlin.Int, kotlin.String, kotlin.String, kotlin.Throwable)/stack)为空或者[tag](print.md#com.drake.logcat.LogCat$print(kotlin.Int, kotlin.String, kotlin.String, kotlin.Throwable)/tag)为空将不会输出日志, 拦截器

### Parameters

`level` - 日志等级

`message` - 日志信息

`tag` - 日志标签

`stack` - 日志异常