## LogCat


<p align="center"><img src="https://i.imgur.com/3NdQ93C.png" alt="1600" width="25%"/></p>

<p align="center"><strong>轻量级的日志工具</strong></p>

<p align="center"><img src="https://i.imgur.com/t3vnPHs.jpg" width="200"/></p>


<p align="center">
<a href="https://jitpack.io/#liangjingkanji/LogCat"><img src="https://jitpack.io/v/liangjingkanji/LogCat.svg"/></a>
<img src="https://img.shields.io/badge/language-kotlin-orange.svg"/>
<img src="https://img.shields.io/badge/license-Apache-blue"/>
<a href="https://liangjingkanji.github.io/LogCat/api/"><img src="https://img.shields.io/badge/api-%E5%87%BD%E6%95%B0%E6%96%87%E6%A1%A3-red"/></a>
<a href="https://jq.qq.com/?_wv=1027&k=vWsXSNBJ"><img src="https://img.shields.io/badge/QQ群-752854893-blue"/></a>
</p>


## 特点

-   日志全局开关
-   日志全局标签
-   日志全局Hook
-   日志代码位置信息
-   格式化输出JSON
-   超长日志分段输出

## 使用

```kotlin
LogCat.setDebug(BuildConfig.DEBUG) // 全局开关

LogCat.d("log message")
```

## 安装

添加远程仓库根据创建项目的 Android Studio 版本有所不同

Android Studio Arctic Fox以下创建的项目 在项目根目录的 build.gradle 添加仓库

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

Android Studio Arctic Fox以上创建的项目 在项目根目录的 settings.gradle 添加仓库

```kotlin
dependencyResolutionManagement {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

然后在 module 的 build.gradle 添加依赖框架

```groovy
implementation 'com.github.liangjingkanji:LogCat:1.2.4'
```



## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

