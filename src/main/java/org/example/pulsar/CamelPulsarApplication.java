package org.example.pulsar;

import org.apache.camel.CamelContext;
import org.apache.camel.component.pulsar.PulsarComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.example.pulsar.config.PulsarConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelPulsarApplication {
    public static final Logger logger = LoggerFactory.getLogger(CamelPulsarApplication.class);

    public static void main(String[] args) throws Exception {
        logger.info("configure Pulsar broker");
        PulsarConfig config = new PulsarConfig();

        logger.info("creating Camel context");
        CamelContext context = new DefaultCamelContext();

        logger.info("adding component and routes");
        PulsarComponent pulsar = new PulsarComponent();
        pulsar.setPulsarClient(config.getClient());
        context.addComponent("pulsar", pulsar);
        context.addRoutes(new Routes());

        logger.info("start component");
        context.start();

        logger.info("send test messages to queue, which will become a local file each on test-messages-pulsar folder");
        Sender sender = new Sender();
        sender.send10MessagesToTestQueue(context);

        logger.info("stopping Camel application");
        context.stop();

        //// WARNING: this whole section below deals with receiving and sending a message to a server. This is not being done here.
        //  get the endpoint from the camel context
//        Endpoint endpoint = context.getEndpoint(PulsarConfig.TEST_URI);
        // create the exchange used for the communication
        // we use the in out pattern for a synchronized exchange where we expect a response
//        Exchange exchange = endpoint.createExchange(ExchangePattern.InOut);
        // set the input on the in body
        // must be correct type to match the expected type of an Integer object
//        exchange.getIn().setBody(11);
        // to send the exchange we need an producer to do it for us
//        Producer producer = endpoint.createProducer();
        // start the producer so it can operate
//        producer.start();
        // let the producer process the exchange where it does all the work in this oneline of code
//        System.out.println("Invoking the multiply with 11");
//        producer.process(exchange);
        // stopping the JMS producer has the side effect of the "ReplyTo Queue" being properly
        // closed, making this client not to try any further reads for the replies from the server
//        producer.stop();
    }
}
