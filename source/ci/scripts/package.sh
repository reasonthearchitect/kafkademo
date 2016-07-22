#!/bin/sh

cd resource-source

export TERM=${TERM:-dumb}

gradle -Dorg.gradle.native=false build

ls build/libs

cp build/libs/source.jar ../resource-jar

cp Dockerfile ../resource-jar
