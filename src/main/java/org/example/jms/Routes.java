package org.example.jms;

import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // the FileComponent (handle file urls) will take each message that arrives here and save to the test directory
        // every message will be saved in a file that corresponds to its destination and message id
        from("test-jms:queue:test.queue").to("file://test-messages");
    }
}
