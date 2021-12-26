package org.example.pulsar;

import org.apache.camel.builder.RouteBuilder;
import org.example.pulsar.config.PulsarConfig;

public class Routes extends RouteBuilder {


    @Override
    public void configure() {
        // the FileComponent (all file urls are redirected to it) will take each message that arrives here and save to the test directory
        // every message will be saved in a file that corresponds to its destination and message id
        from(PulsarConfig.TEST_URI).to("file://test-messages-pulsar");
    }
}
