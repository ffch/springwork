没有对netty研究很深，先做一个demo出来，之前对netty的启动方式做了一些研究，包括自定义注解之类的，
不过最后发现还是用netty的@shareable即可，其他方式可以自己去手动写。

1.增加了3个handler，closeFutureHandler，exceptionFutureHandler，bussinessFutureHandler。
bussinessFutureHandler必须有，处理业务逻辑使用。方便使用，使用责任链模式处理业务。



