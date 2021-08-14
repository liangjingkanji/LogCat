LogCat可以打印出日志堆栈信息, 方便追踪调用顺序

```kotlin
LogCat.d("日志信息", stack = STACK_INFO)
```

输出结果

<img src="https://i.loli.net/2021/08/14/jZ6hyz4RAHtcJdK.png" width="700"/>

<br>
可以看到追踪到: `onActivityCreated` -> `functionA` -> `functionB`, 最终日志是在B出被调用

> `STACK_INFO`属于一个全局变量对象, 直接使用即可, 也可以传递, 堆栈追踪会一直到创建此变量处

