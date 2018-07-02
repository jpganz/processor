FROM openjdk:8-jdk-alpine

MAINTAINER  Juan Hernandez <jpganz18@gmail.com>

COPY ./zuul/target/zuul.jar /opt/apps/zuul.jar

EXPOSE 8762

CMD ["java", "-jar", "/opt/apps/zuul.jar"]
