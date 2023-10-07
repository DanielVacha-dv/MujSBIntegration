//package cz.danes.mujsbintegration.tcp;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.ip.dsl.Tcp;
//
///**
// * Config bean for service activator with  input channel , and output-channel which use
// * response-channel and send message back to putty
// * use only SomeService beans
// */
//@Configuration
//@EnableIntegration
//public class TcpConfig {
//
//    @Value("${tcp.server.port}")
//    private int serverPort;
//    @Autowired
//    SomeService someService;
//
//
//    /**
//     * @return hotove integracniflow
//     */
//    @Bean
//    public IntegrationFlow tcpServer() {
//        return IntegrationFlow.from(Tcp.inboundGateway(Tcp.netServer(serverPort)))
//                .handle(someService, "exampleHandler")
//                .get();
//    }
//
//}
