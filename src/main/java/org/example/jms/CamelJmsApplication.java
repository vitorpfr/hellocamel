package org.example.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.ConnectionFactory;

public class CamelJmsApplication {
    public static final Logger logger = LoggerFactory.getLogger(CamelJmsApplication.class);

    public static void main(String[] args) throws Exception {
        logger.info("creating Camel context");
        CamelContext context = new DefaultCamelContext();

        logger.info("adding components");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        logger.info("adding routes");
        context.addRoutes(new Routes());

        logger.info("initializing camel");
        context.start();

        logger.info("sending 10 test messages to a queue routed to the test-jms component");
        Sender sender = new Sender();
        sender.send10MessagesToTestQueue(context);

        logger.info("stopping camel application");
        context.stop();
    }
}
