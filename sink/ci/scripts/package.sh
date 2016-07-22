#!/bin/sh

cd resource-sink

export TERM=${TERM:-dumb}

gradle -Dorg.gradle.native=false build

ls build/libs

cp build/libs/sink.jar ../resource-jar

cp Dockerfile ../resource-jar
