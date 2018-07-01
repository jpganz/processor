#FROM alpine:latest
#FROM 3.5.4-jdk-8-alpine
FROM openjdk:8-jdk-alpine
#VOLUME /tmp
ARG JAR_FILE
#COPY .* /tmp
#COPY ./docker/entrypoint.sh /tmp
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

MAINTAINER  Juan Hernandez <jpganz18@gmail.com>
