# Find store service

Application that shows the 5 closest stores to a given position.

## Stage Version
You can try the app here http://35.233.254.61/

## Context Diagram

`Deployment`
https://www.lucidchart.com/documents/view/0f1c8b62-be54-4062-b269-79f54fa5455a

## Components

`UI`
* findstore-ui repository https://github.com/camiloahm/findstore-ui

`Service`
* findstore-service repository https://github.com/camiloahm/findstore/

## Service Architecture

The application is built as a microservice with is own Dockerfile and its own springboot context

I tried to keep all the variables immutable and responsibilities clear.

Patterns used:
* Singleton
* Builder 
* Dependency Injection
* Static Factory Method
* Intercepting Filter

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
* Spring
