package cz.danes.mujsbintegration.tcp;

import org.apache.tomcat.util.net.NioEndpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
@EnableIntegration
public class TcpConfig {

    @Value("${tcp.server.port}")
    private int serverPort;


    /**
     * @return hotove integracniflow
     * problems when send over putty response is verz slow for second and next messages
     */
    @Bean
    public IntegrationFlow tcpServer() {
        SomeService someService = new SomeService();
        return IntegrationFlow.from(Tcp.inboundGateway(Tcp.netServer(serverPort)))
                .handle(someService, "exampleHandler")
                .get();
    }

    public class SomeService {

        @ServiceActivator(outputChannel = "exampleChannel")
        public void exampleHandler(Message message) {
            System.out.println("exampleHandler message:" + (new String((byte[]) message.getPayload(), StandardCharsets.UTF_8)));
        }

    }

}
