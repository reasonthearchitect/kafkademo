# Kafka Demo

This repo contains the code that is required to run the demo application on your local machine.

## Prerequisite

In order to run the demo you will need to have the following on your machine.

* Java 8

* Gradle

* Kafka and Zookeeper

# Running Kafka Locally

You can install Kafka locallyby [downloading the binary for your system](http://kafka.apache.org/downloads.html).

```
For detailed instructions please refer to [the documentation](http://kafka.apache.org/documentation.html#introduction)
```

Once installed, and unzipped into a directory, `cd` into the unzipped directory.

From the root kafka directory, run the following to start zookeeper:

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

Once zookeeper has started you can start Kafka with the following:

```
bin/kafka-server-start.sh config/server.properties
```

