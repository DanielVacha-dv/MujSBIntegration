package cz.danes.mujsbintegration.tcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.nio.charset.StandardCharsets;

/**
 * handler wich use defined input and output channel to communicate
 */
@MessageEndpoint
@DependsOn("myOutputChannel")
public class HandlerEndpoint {

    @Autowired
    @Qualifier("myOutputChannel")
    MessageChannel outputChannel;

    // when no output-channel defined use response channel as output channel
    @ServiceActivator(inputChannel = "myInputChannel")
    public String exampleHandlerEndpoint(Message message) {
        String s = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        System.out.println("exampleHandlerEndpoint message:" + s);
        outputChannel.send(MessageBuilder.withPayload(message.getPayload()).build());
        return "HandlerEndpoint service " + s;
    }


}
