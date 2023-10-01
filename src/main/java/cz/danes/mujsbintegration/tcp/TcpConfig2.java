//package cz.danes.mujsbintegration.tcp;
//
////import cz.danes.mujsbintegration.tcp.SomeService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.ip.dsl.Tcp;
//
///**
// * Config bean for service activator with  input channel , and output-channel
// * use HandlerEndpoint and MyChannels beans
// */
//@Configuration
//@EnableIntegration
//@DependsOn("handlerEndpoint")
//public class TcpConfig2 {
//
//    @Value("${tcp.server.port}")
//    private int serverPort;
//    @Autowired
//    HandlerEndpoint someService;
//
//
//    /**
//     * @return
//     */
//    @Bean
//    public IntegrationFlow tcpServer() {
//        return IntegrationFlow.from(Tcp.inboundGateway(Tcp.netServer(serverPort)))
//                .channel("myInputChannel")
//                .handle(someService, "exampleHandlerEndpoint")
//                .get();
//    }
//
//
//
//    @ServiceActivator(inputChannel = "myOutputChannel")
//    public void consumeStringMessage(String message) {
//        System.out.println("Received message from myOutputChannel : " + message);
//    }
//}
