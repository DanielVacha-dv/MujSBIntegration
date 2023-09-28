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

/**
 * create router with different configuration
 */
@Configuration
@EnableIntegration
@Slf4j
public class MyRouterConfig {

    @Bean
    public IntegrationFlow myIntegrationFlowRouterContent(Supplier<Message<String>> messageSupplier) {
        return IntegrationFlow.fromSupplier(messageSupplier, e -> e.poller(p -> p.fixedRate(10000)))
                .route(String.class, m -> m.length() % 2 == 0 ?
                        "stringChannel2Su" : "stringChannel1Li")
                .get();
    }

    @Bean(name = "stringChannelHandler1li")
    @ServiceActivator(inputChannel = "stringChannel1Li")
    public MessageHandler stringChannel1Li() {
        MessageHandler m = message -> System.out.println("1 odd  " + message.getPayload());
        return m;
    }

    @Bean(name = "stringChannelHandler2Su")
    @ServiceActivator(inputChannel = "stringChannel2Su")
    public MessageHandler stringChannel2Su() {
        MessageHandler m = message -> System.out.println("2 even " + message.getPayload());
        return m;
    }

    //    @Bean
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
