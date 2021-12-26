This is a test project to explore Gradle and Apache Camel.

It uses as a reference the official Apache Camel's user manual (https://camel.apache.org/manual/walk-through-an-example.html)

It does the following:
- Starts a Camel application/context
- If running Pulsar task, add configuration (cluster, tenant, namespace)
- Adds JMS/ActiveMQ or Pulsar (depending on task ran) component and a queue routing to it
- Sends test messages to both queues, which become local files (in test-messages or test-messages-pulsar)
- Stops Camel application/context

To build: `./gradlew build`

To run the JMS/ActiveMQ example: `./gradlew runJms`
To run the Pulsar example: `./gradlew runPulsar`

The Pulsar example requires the Pulsar cluster running on Docker:
```
docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  -v $PWD/.pulsar/data \
  apachepulsar/pulsar:latest \
  bin/pulsar standalone
  ```