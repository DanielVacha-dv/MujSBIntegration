package cz.danes.mujsbintegration.myrouter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.function.Supplier;

@Configuration
@EnableIntegration
@Slf4j
public class MyRouterConfig {



//    @Bean
//    public IntegrationFlow myIntegrationFlowRouterContent1(Supplier<Message<String>> messageSupplier) {
//        return IntegrationFlow.fromSupplier(messageSupplier, e -> e.poller(p -> p.fixedRate(10000)))
//                .channel(MessageChannels.queue())
//                .<Object, Class<?>>route(Object::getClass, r -> r
//                        .subFlowMapping("even".getClass(), sf -> sf.channel("akanal"))
//                        .subFlowMapping("odd", sf -> sf.channel("bkanal"))
//                ).get();
//    }
//    @Bean
//    public IntegrationFlow myIntegrationFlowRouterContent(Supplier<Message<String>> messageSupplier) {
//        return IntegrationFlow.fromSupplier(messageSupplier, e -> e.poller(p -> p.fixedRate(10000)))
//                .channel(MessageChannels.queue())
//                .route(Message.class, m -> m
//                        .channelMapping(String.class, "stringChannel")
//                        .channelMapping(String.class, "stringChannel2"))
//                .get();
//    }

    @Bean
    public IntegrationFlow myIntegrationFlowRouterRecipient(Supplier<Message<String>> messageSupplier) {
        return IntegrationFlow.fromSupplier(messageSupplier, e -> e.poller(p -> p.fixedRate(10000)))
                .routeToRecipients(r -> r
                        .applySequence(true)
                        .ignoreSendFailures(true)
                        .recipient("stringChannel")
                        .recipient("stringChannel2")
                        .sendTimeout(1_234L))
                .get();
    }

    @Bean
    public MessageChannel stringChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean(name = "stringChannelHandler")
    @ServiceActivator(inputChannel = "stringChannel")
    public MessageHandler channelToConsoleWriteA() {
        MessageHandler m = message -> System.out.println("1 kurna sprava " + message.getPayload());
        return m;
    }

    @Bean
    public MessageChannel integerChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean(name = "stringChannelHandler2")
    @ServiceActivator(inputChannel = "stringChannel2")
    public MessageHandler channelToConsoleWriteB() {
        MessageHandler m = message -> System.out.println("2 kurna sprava " + message.getPayload());
        return m;
    }
}
