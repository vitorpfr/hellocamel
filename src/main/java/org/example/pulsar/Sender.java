package org.example.pulsar;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.example.pulsar.config.PulsarConfig;

public class Sender {
    public void send10MessagesToTestQueue(CamelContext context) {
        ProducerTemplate template = context.createProducerTemplate();

        // sends 10 messages to the test.queue queue
        for (int i = 0; i < 10; i++) {
            System.out.println("Sending message " + i);
            template.sendBody(PulsarConfig.TEST_URI, "Test message: " + i);
        }
    }
}
