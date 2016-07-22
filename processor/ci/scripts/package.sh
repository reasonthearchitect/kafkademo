#!/bin/sh

cd resource-processor

export TERM=${TERM:-dumb}

gradle -Dorg.gradle.native=false build

ls build/libs

cp build/libs/processor.jar ../resource-jar

cp Dockerfile ../resource-jar
