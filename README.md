# Find Store

The project is also available here https://github.com/camiloahm/findstore

## Architecture

The application is built as a microservice with is own Dockerfile and its own springboot context

I tried to keep all the variables immutable and responsibilities clear.

Patterns used:
* Singleton
* Builder 
* Dependency Injection
* Static Factory Method

### Build
The App is packaged with Gradle, to build and run this app you can execute in the root file this command
```
$ ./gradlew build && java -jar build/libs/findstore-1.0.jar 
```

### Dependency Injection Framework

This is a web app, so I decided to use Spring. I use Spring Rest to expose the rest API, springboot to package the app and Spring dependency Injection to invert control and decouple in modules and services.  


### Logs

Logs are ready for production, they are configure with log4J  

### Utilities

I used these libraries to improve code quality and maintainability 

* Lombok
* SLF4
* Guava 
* AssertJ
* VAVR
