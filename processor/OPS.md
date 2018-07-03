#Processor Properties

## Property = Value

| Property | Value | Description
| -------- | ----------- |
| spring.data.mongodb.host | mongo | Rabbit MQ Host
| spring.data.mongodb.port | 27017 | 
| spring.data.mongodb.database | processor | 
| spring.rabbitmq.host | rabbitmq | 
| spring.rabbitmq.port | 5672 | 
| spring.rabbitmq.username | juan | 
| spring.rabbitmq.password | juan123 | 
| rabbitmq.topicexchange.name | falcon-exchange | 
| rabbitmq.consumerqueue.name | messages | 
| rabbitmq.queuerouting.key | mykey | 
| rabbitmq.savedmessagesrouting.key | savedmessages | 
| spring.application.name | processor-client | 
| server.port | 0 | 
| eureka.client.serviceUrl.defaultZone | http://eureka:8761/eureka | 
| eureka.instance.prefer-ip-address | true | 
