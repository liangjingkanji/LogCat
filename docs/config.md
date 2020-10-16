推荐在Application中配置日志


```kotlin
LogCat.config {
    defaultTag = "默认日志"
    enabled = BuildConfig.DEBUG
    addInterceptor(LogStoreInterceptor())
}
```

> 都是可选项, 不要求一定配置

| 函数 | 描述 |
|-|-|
| defaultTag | 默认日志标签, 默认值为`"日志"` |
| enabled | 是否开启日志, `BuildConfig.DEBUG`表示仅在DEBUG开发调试才会启用日志输出 |
| addInterceptor | 添加日志拦截器, 拦截器可以添加多个, 并且按照添加顺序依次拦截日志 |
