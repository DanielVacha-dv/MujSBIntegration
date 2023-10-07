package cz.danes.mujsbintegration.tcp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

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
        String sendAndReceive(String message);
    }
}
