Property and values:

Processor:
spring.data.mongodb.host=mongo
spring.data.mongodb.port=27017
spring.data.mongodb.database=processor
spring.rabbitmq.host=rabbitmq
spring.rabbitmq.port=5672
spring.rabbitmq.username=juan
spring.rabbitmq.password=juan123
rabbitmq.topicexchange.name=falcon-exchange
rabbitmq.consumerqueue.name=messages
rabbitmq.queuerouting.key=mykey
rabbitmq.savedmessagesrouting.key=savedmessages
spring.application.name=processor-client
server.port=0
eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka
eureka.instance.prefer-ip-address=true



Zuul:

server.port=8762
eureka.client.register-with-eureka=false
eureka.client.registry-fetch-interval-seconds=5
eureka.client.fetch-registry=true
eureka.client.eureka-server-connect-timeout-seconds=1
zuul.ignored-services='*'
zuul.routes.processor-client.path=/**
zuul.routes.processor-client.service-id=processor-client
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=60000
ribbon.ReadTimeout=60000
ribbon.ConnectionTimeout=60000
eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka



Eureka:

server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.server.wait-time-in-ms-when-sync-empty=0
spring.application.name=eureka-server
