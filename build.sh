#!/bin/sh
cd $TRAVIS_BUILD_DIR/findstore-service
./gradle assemble
./gradlew check
./gradlew codeCoverageReport  
  
