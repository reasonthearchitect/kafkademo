# Kafka Demo

This repo contains the code that is required to run the demo application on your local machine.

The scenario of the following POC is that a user will: 

* POST to the Source service

* The Source service will place the message on the `catin` topic

* The processor service will receive the message, convert it to a different object, capitalize the first letter, and set the age as 1. For example, if you send in a message with the name as `catOne` this service will change the name to `CatOne`

* The service will then post the message on the `dogin` topic

* The Sink service will receive the message and print it out to the console

The scenario appears as follows:

![Overview](overview.png)

## Note

All the components in the repository where generated with the [spring-rest-kafka generator](https://github.com/reasonthearchitect/generator-spring-rest)

## Video Walkthrough

The following is [a video walkthrough of the application](https://youtu.be/Zariq8tqK8A)

## Prerequisite

In order to run the demo you will need to have the following on your machine.

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

* [Gradle](https://gradle.org/gradle-download/)

* Kafka and Zookeeper: See below

## Running Kafka Locally

You can install Kafka locallyby [downloading the binary for your system](http://kafka.apache.org/downloads.html).

For detailed instructions please refer to [the documentation](http://kafka.apache.org/documentation.html#introduction)

Once installed, and unzipped into a directory, `cd` into the unzipped directory.

From the root kafka directory, run the following to start zookeeper:

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

Once zookeeper has started you can start Kafka with the following:

```
bin/kafka-server-start.sh config/server.properties
```

## Running The Application Containers

Once you have installed the containers, you will need to start the 3 services before running the test case.

### Run Rest/Source Service

Run the service:

```
cd source
gradle bootrun
```
Once the container is up and running... in a separate command prompt, from the Kafka folder (from above), you can monitor the topic by running the following command. Note that you must wait for the service to start otherwise you will receive an error.

```
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic catin --from-beginning
```

### Run Processor Service

From this projects root directory, run the following commands:

```
cd processor
gradle bootrun
```
Once the container is up and running... in a separate command prompt, from the Kafka folder (from above), you can monitor the topic by running the following command. Note that you must wait for the service to start otherwise you will receive an error.

```
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic dogin --from-beginning
```

### Running Sink Service

From this projects root directory, run the following commands:

```
cd sink
gradle bootrun
```
Once the container is up and running... Keep the window open as the final message will print out to the log. This will be the successful completion of the message chain.

## Push A Message

Once everything is set up, you can push a message through the system and see the output in the Sink services console.

To run, from a separate command prompt. Note that you can change the `catOne` argument to anything and run the command again.

```
curl -i -H "Content-Type: application/json" -X POST -d '{"name":"catOne"}' http://localhost:8090/cats
```

In the Sink Service command prompt window, you will see the following:

```
[2016-07-22 12:25:48.436] boot - 43141  INFO [kafka-binder-1] --- SinkSink: 
{
  "name" : "CatOne",
  "age" : 1
}
```

