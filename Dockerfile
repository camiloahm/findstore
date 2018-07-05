FROM openjdk:8-jdk-alpine
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
COPY build/libs/findstore-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]