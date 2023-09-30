package cz.danes.mujsbintegration;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.nio.charset.StandardCharsets;

/**
 * add functionality of Service activator
 */
@MessageEndpoint
public class SomeService {

    // when no output-channel defined use response channel as output channel
    @ServiceActivator//(outputChannel = "stringOutputChannel")
    public String exampleHandler(Message message) {
        String s = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        System.out.println("exampleHandler message:" + s);
        return "Some service " + s;
    }

}
