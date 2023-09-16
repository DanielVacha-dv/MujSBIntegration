//package cz.danes.mujsbintegration.tcp;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.MessageChannels;
//import org.springframework.integration.ip.dsl.Tcp;
//import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
//import org.springframework.messaging.SubscribableChannel;
//
//@Slf4j
//@EnableIntegration
////@Import(DomainConfig.class)
//@Configuration(proxyBeanMethods = false)
//public class TcpConfiguration {
//
//
//    @Bean
//    public TcpMessageDeserializer tcpMessageSerializer() {
//        return new TcpMessageDeserializer();
//    }
//
//    @Bean
//    public TcpNetServerConnectionFactory tcpServerFactory(
//            DefaultsProperties defaults,
//            TcpMessageDeserializer tcpMessageDeserializer
//    ) {
//        TcpNetServerConnectionFactory factory = new TcpNetServerConnectionFactory(defaults.getPort());
//        factory.setDeserializer(tcpMessageDeserializer);
//        return factory;
//    }
//
//    @Bean(TcpMessageReceived.TCP_MESSAGE_RECEIVED_SUBSCRIBABLE_CHANNEL_NAME)
//    public SubscribableChannel carReceivedChannel() {
//        return MessageChannels.publishSubscribe(TcpMessageReceived.TCP_MESSAGE_RECEIVED_SUBSCRIBABLE_CHANNEL_NAME).getObject();
//    }
//
////    @Bean
////    public IntegrationFlow carEvidenceFlow(
////            TcpNetServerConnectionFactory tcpNetServerConnectionFactory,
////            TcpMessageHandler tcpMessageHandler
////    ) {
////        return IntegrationFlow.from(Tcp.inboundGateway(tcpNetServerConnectionFactory))
////                .handle(tcpMessageHandler)
////                .channel(TcpMessageReceived.TCP_MESSAGE_RECEIVED_SUBSCRIBABLE_CHANNEL_NAME)
////                .get();
////    }
//}
