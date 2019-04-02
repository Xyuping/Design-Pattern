# 事件驱动异步全量加载数据到缓存中

以事件驱动的方式将数据加载到缓存中。设置一个事件总线，所有事件（INIT、LOAD_SUCCESS、RELOAD）先抛给事件总线，由事件总线消费事件，将其发送给控制器。再由控制器将不同事件分派给对应模块来完成。

在本例中，只有 cache 这一模块。cache 有三个状态（init、loading、reload）。当cache 接收到事件后，会在当前状态下处理该事件。

在 init 状态下，收到INIT事件，cache 开始 load 数据（由于 load 一般比较耗时，所以另开一个线程，load 完之后向事件总线发送LOAD_SUCESS事件），然后设置 cache 状态为 loading。

在 loading 状态下，如果收到LOAD_SUCCESS事件，调用 cache 的 update方法，并启动一个定时器（另起一个线程），每隔一定时间会向事件总线发送RELOAD事件；

在 running 状态下如果收到RELOAD事件，则调用 load方法。（此状态与init 状态类似）



由状态来完成整个调度，由cache 来完成具体功能。（状态知道整个流程，cache 只知道当前的动作。例如，cache 知道怎么该从哪里load数据（数据库或者其他地方），知道怎么从数据库中load 数据等等。但是状态知道什么时候开始 load，load 完之后要干什么）。



## tips：

1. 可以用单例模式获得cache、eventbus、controller、状态。
2. 用线程池可以避免开启太多线程导致内存溢出
3. 事件队列可用阻塞队列

