## Serial GC算法
Serial GC 是一种单线程执行的 标记-整理 GC算法，适用于对GC延迟不敏感的客户端java程序。另外，如果很多个jvm运行在同一台机器上时，也适合使用这种算法。参数：
```text
-XX:+UseSerialGC
```

## Parallel GC
JAVA8 的默认GC策略，使用多线程进行年轻代收集，是Serial的多线程版本，当主机有N个 CPU 时，默认会使用N个线程

```text
-XX:+UseParallelGC
-XX:ParallelGCThreads=<线程数目>
```
在单核主机上，是不会使用Parallel GC的，在2核主机上，Parallel GC 可能和默认的 GC 收集器工作效率相同，而在大于2核的主机上，Parallel GC 一般会有更好的表现。

Parallel GC适合于需要高吞吐量而对暂停时间不敏感的场合，比如批处理任务。

另外，使用-XX:+UseParallelOldGC命令行选项可以让新生代和老年代同时使用多线程并行垃圾收集。在老年代，ParallelOld是一种 标记-整理 算法，会在标记后将存活对象搬运到一起来防止出现内存碎片。

## Concurrent Mark Sweep (CMS)
CMS 是一种希望最小化响应时间的垃圾收集算法，GC的某些步骤可以与应用线程并行的进行，它的收集周期为：
- 初始标记(CMS-initial-mark) ：标记 Roots 能直接引用到的对象
- 并发标记(CMS-concurrent-mark)：进行 GC Root Tracing
- 重新标记(CMS-remark) ：修正并发标记期间由于用户程序运行而导致的变动
- 并发清除(CMS-concurrent-sweep)：进行清除工作
  
初始标记和重新标记会导致 stop the world。由于最耗时的并发标记和并发清除都可以和用户程序同时进行，所以其实可以认为 GC 和用户程序是同时进行的。

CMS的一个劣势是对CPU资源比较敏感，不过现代的后端系统通常是重IO的，所以个人感觉影响并不会太大。另外一个问题是，由于 GC 和用户程序同时进行，可能会有部分新产生的垃圾无法被直接回收，需要等到下一次 GC 时再回收。也是由于在垃圾收集阶段用户线程还需要运行，那也就还需要预留有足够的内存空间给用户线程使用，因此CMS收集器不能像其他收集器那样等到老年代几乎完全被填满了再进行收集，需要预留一部分空间提供并发收集时的程序运作使用。

```text
-XX:+UseConcMarkSweepGC
CMS默认回收线程数目是 (ParallelGCThreads + 3)/4)
```

## G1 GC
使用 G1GC 时，Java 堆的内存布局与其他的收集器有很大区别，它将整个 Java 堆划分成多个大小相等的独立区域（Region），虽然还是有年轻代和老年代的概念，但是新生代和老年代不是物理隔离的，它们都是一部分 Region的集合，并且这些Region无需在物理上连续。一般建议当机器内存较大（6G以上），且之前使用的GC不能满足需求时才使用G1GC。

```text
-XX:+UseG1GC 使用G1GC
-XX:MaxGCPauseMillis=n 最大暂停时间，jvm不保证做到 单位为毫秒
-XX:InitiatingHeapOccupancyPercent=n 启动一个并发垃圾收集周期所需要达到的整堆占用比例。这个比例是指整个堆的占用比例而不是某一个代，如果这个值是0则代表‘持续做GC’。默认值是45
-XX:MaxTenuringThreshold=n 经过多少轮minor GC，对象会进入老年代
-XX:ParallelGCThreads=n 并行阶段使用线程数
-XX:ConcGCThreads=n 并发垃圾收集的线程数目
-XX:G1ReservePercent=n 防止晋升老年代失败而预留的空闲区域的数目
-XX:G1HeapRegionSize=n 空闲区域大小 最小1Mb 最大 32Mb.
```