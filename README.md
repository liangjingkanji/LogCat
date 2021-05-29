## LogCat


<p align="center"><img src="https://raw.githubusercontent.com/liangjingkanji/LogCat/master/docs/img/logo.gif" width="25%"/></p>

<p align="center"><strong>全局日志工具</strong></p>

<p align="center"><a href="http://liangjingkanji.github.io/LogCat/">使用文档</a> | <a href="https://coding-pages-bucket-3558162-8706000-16645-587723-1252757332.cos-website.ap-shanghai.myqcloud.com/">备用访问</a></p>
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
-   日志全局标签TAG
-   日志全局开关
-   日志全局拦截器
-   打印日常信息
-   输出JSON
-   输出超长字符串
-   日志堆栈输出
-   自定义日志等级
-   Kotlin特性

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
implementation 'com.github.liangjingkanji:LogCat:1.1.0'
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

