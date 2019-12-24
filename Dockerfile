FROM openjdk:11-jdk-alpine

LABEL maintainer="joostvherwaarden@hotmail.com"

VOLUME /tmp

EXPOSE 8100

ARG JAR_FILE

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar"]
