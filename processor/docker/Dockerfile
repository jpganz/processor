FROM openjdk:8-jdk-alpine

MAINTAINER  Juan Hernandez <jpganz18@gmail.com>

COPY ./processor/target/processor.jar /opt/apps/processor.jar

EXPOSE 0-65535

CMD ["java", "-jar", "/opt/apps/processor.jar"]
