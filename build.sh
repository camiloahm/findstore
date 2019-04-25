./#!/bin/sh
cd $TRAVIS_BUILD_DIR/findstore-service
./gradlew build
./gradlew assemble
./gradlew check
./gradlew codeCoverageReport  
  
