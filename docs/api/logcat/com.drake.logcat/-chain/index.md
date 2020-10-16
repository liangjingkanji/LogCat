[logcat](../../index.md) / [com.drake.logcat](../index.md) / [Chain](./index.md)

# Chain

`data class Chain`

日志拦截器的链式对象

### Parameters

`level` - 等级

`message` - 信息

`tag` - 标签

`stack` - 异常

`cancel` - 是否终止

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | 日志拦截器的链式对象`Chain(level: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, stack: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`? = null, cancel: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false)` |

### Properties

| Name | Summary |
|---|---|
| [level](level.md) | 等级`var level: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [message](message.md) | 信息`var message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [stack](stack.md) | 异常`var stack: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?` |
| [tag](tag.md) | 标签`var tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [cancel](cancel.md) | 取消日志打印`fun cancel(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
