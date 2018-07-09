FROM openjdk:8-jdk-alpine
VOLUME /tmp
#If you choose docker gradle plug in uncomment next lines
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
COPY build/libs/findstore-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]