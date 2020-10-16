优雅的日志工具

## 普通日志

```kotlin
LogCat.v("日志信息")
```

函数介绍

```kotlin
fun v(message: String?, tag: String? = this.defaultTag, stack: Throwable? = null)
```

存在多个参数一致的相似函数用于打印日志, 对应Android自带的`Log`的函数

| 函数 | 描述 |
|-|-|
| v | 普通日志 |
| i | 日志信息 |
| d | 调试日志 |
| w | 警告日志 |
| e | 错误日志 |
| wtf | 错误日志 |

## 输出JSON

LogCat可以输出格式化好的JSON日志

```kotlin
LogCat.json("JSON字符串")
```

## 安全性

- 超长字符也能在AndroidStudio的LogCat完整打印
- 如果Tag为`Null` (默认使用`LogCat.defaultTag`), 将不会输出日志
- 日志消息可以为`Null`或者空, 都将输出空 (而官方的Log则会抛出异常崩溃)

