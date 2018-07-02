FROM openjdk:8-jdk-alpine

MAINTAINER  Juan Hernandez <jpganz18@gmail.com>

COPY ./eureka/target/eureka.jar /opt/apps/eureka.jar

EXPOSE 8761

CMD ["java", "-jar", "/opt/apps/eureka.jar"]
