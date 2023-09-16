package cz.danes.mujsbintegration.tcp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableIntegration
public class TcpConfig {

    @Value("${tcp.server.port}")
    private int serverPort;
    private PayloadLengthFilter payloadLengthFilter;

//    @Autowired
//    TcpMessageTransformer tcpMessageTransformer;

    @Bean
    public PayloadLengthFilter getPayloadLengthFilter() {
        return new PayloadLengthFilter(10);
    }

    /**
     * @return hotove integracniflow
     */
    @Bean
    public IntegrationFlow tcpServer() {
        // integracni flow zacina vstupnim TCP portem
        PayloadLengthFilter payloadLengthFilter1 = new PayloadLengthFilter(10);
        return IntegrationFlow.from(Tcp.inboundGateway(Tcp.netServer(serverPort)))
//                .transform(payload -> "Server: Hello, " + (char) payload)
                // transformace vstupnich dat
//                .filter(message, payload -> message.toString().length() > 10)
                .filter(payloadLengthFilter1)
                .transform(payload -> "Server: Hello, " + (new String((byte[]) payload, StandardCharsets.UTF_8)))
//                .transform(tcpMessageTransformer, "transform")
//                .transform(Transformers.objectToString())
                .get();
    }

    @Bean
    @ServiceActivator(inputChannel = "toTcp")
    public TcpOutboundGateway tcpOutboundGateway() {
        TcpOutboundGateway gateway = new TcpOutboundGateway();
        gateway.setConnectionFactory(tcpConnectionFactory());
        return gateway;
    }


    @Bean
    public AbstractClientConnectionFactory tcpConnectionFactory() {
        return Tcp.netClient("localhost", serverPort).getObject();
    }

    @MessagingGateway(defaultRequestChannel = "toTcp")
    public interface TcpGateway {
        String sendAndReceive(String message);
    }
}
