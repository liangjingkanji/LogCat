# LogCat



特性

-   普通支持输出
-   JSON格式化输出
-   超大字符串输出
-   日志全局开关
-   日志全局TAG
-   日志树
-   Kotlin默认函数支持





## 安装

project 的 build.gradle

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```



module 的 build.gradle

```groovy
implementation 'com.github.liangjingkanji:LogCat:1.0'
```



## 普通日志

```
LogCat.v
LogCat.i
LogCat.d
LogCat.w
LogCat.e
```



所有函数遵守以下参数顺序

```
fun v(message: String, tag: String = defaultTag, t: Throwable? = null)
```



>    输出超大字符串

```
LogCat.big(message, tag)
```



## JSON

示例

```kotlin
LogCat.json(getString(R.string.json))
```



该函数可以格式化JSON输出, 如果字符串不是JSON会原样输出

```kotlin
fun json(message: String?, tag: String = defaultTag, url: String? = null)
```



![image-20191114155032432](/Users/drake/Library/Application Support/typora-user-images/image-20191114155032432.png)





## 全局TAG|开关

可以单独设置一个参数

```
 LogCat.setConfig("日志", true)
```



## 日志树

和 Timer 框架类似的日志树, 每个日志输出内容都会被监听器回调

```kotlin
LogCat.addTree(object : Tree {
    override fun log(message: String, tag: String, t: Throwable?, level: LogLevel) {
        
    }
})
```



