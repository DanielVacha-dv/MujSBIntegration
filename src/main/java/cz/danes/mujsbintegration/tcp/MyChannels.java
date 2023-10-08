package cz.danes.mujsbintegration.tcp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

import java.nio.charset.StandardCharsets;

@Configuration
public class MyChannels {

    @Bean(name = "myOutputChannel")
    public MessageChannel myChannelOut() {
        return MessageChannels.direct().getObject();
    }

    @Bean(name = "myInputChannel")
    public MessageChannel myChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean(name = "myInputChannel2")
    public MessageChannel myInputChannel2() {
        return MessageChannels.direct().getObject();
    }

    @MessagingGateway(defaultRequestChannel = "myInputChannel2")
    public interface TcpGateway {
        void sendAndReceive(String message);
    }
    @ServiceActivator(inputChannel = "myInputChannel2")
    public void listenMyInputChannel2(String  message) {
        String s = new String(message.getBytes(), StandardCharsets.UTF_8);
        System.out.println("listenMyInputChannel2 message:" + s);

    }
}
