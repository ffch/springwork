注意事项：

1. activemq catch到RuntimeException并不报错，如果没开启事务回滚，会看不出来哪个地方出错（system.out不会打印，也许log4j会？）最后消息还是会被消费掉的。

2. 在消息监听器上加上<property name="sessionTransacted" value="true"/>  
	这样，会重试6次，最后消息还是会被消费掉的。