# Hello Camel

This is a Java small project to explore Apache Camel, using Gradle as a dependency/lifecycle manager.

It builds upon the official Apache Camel's user manual (https://camel.apache.org/manual/walk-through-an-example.html), focusing on using the `camel-pulsar` dependency that handles Apache Pulsar queues, which is not covered in depth in the tutorial.

It does the following:
- Starts a Camel application/context
- If running Pulsar task, add configuration (cluster, tenant, namespace)
- Adds JMS/ActiveMQ or Pulsar (depending on task ran) component and a queue routing to it
- Sends test messages to the configured queue (Sender class), which become local files (in test-messages or test-messages-pulsar)
- Stops Camel application/context

To build: `./gradlew build`

There are two tasks available: run the Camel service that uses either JMS/ActiveMQ protocol or Pulsar protocol: 
- To run the JMS/ActiveMQ example: `./gradlew runJms`
- To run the Pulsar example: `./gradlew runPulsar`

The Pulsar example requires the Apache Pulsar cluster running on Docker:
```
docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  -v $PWD/.pulsar/data \
  apachepulsar/pulsar:latest \
  bin/pulsar standalone
  ```