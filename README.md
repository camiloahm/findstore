# Find Store

The project is also available here https://github.com/camiloahm/findstore

## Architecture

The application is built as a microservice with is own Dockerfile and springboot context

I try to keep all the variables immutable and responsibilities clear.

Patterns used:
* Singleton
* Builder 
* Dependency Injection
* Static Factory Method

### Build
The App is packaged with Gradle, to build this app you can execute in the root 
```
$ ./gradlew build && java -jar build/libs/findstore-1.0.jar 
```

### Dependency Injection Framework

This is a web app, so I decided to use a Spring. I use Spring Rest to expose the rest API, spring boot to package the app and Spring dependency Injection   


### Logs

Logs are ready for production, they are configure with log4J  

### Utilities

I used these libraries to improve code quality and maintainability 

* Lombok
* SLF4
* Guava 
* VAVR
* AssertJ