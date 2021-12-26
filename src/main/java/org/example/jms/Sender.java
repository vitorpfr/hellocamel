package org.example.jms;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

public class Sender {
    public void send10MessagesToTestQueue(CamelContext context) {
        ProducerTemplate template = context.createProducerTemplate();

        // sends 10 JMS messages to the test.queue queue
        for (int i = 0; i < 10; i++) {
            System.out.println("Sending message " + i);
            template.sendBody("test-jms:queue:test.queue", "Test message: " + i);
        }
    }
}
