//package cz.danes.mujsbintegration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.Pollers;
//import org.springframework.integration.file.FileReadingMessageSource;
//import org.springframework.integration.file.FileWritingMessageHandler;
//
//import java.io.File;
//
//@Configuration
//public class IntegrationConfig {
//    @Autowired
//    private FileTransformer transformer;
//
//    @Bean
//    public FileReadingMessageSource fileReader() {
//        FileReadingMessageSource source = new FileReadingMessageSource();
//        source.setDirectory(new File("source"));
//        return source;
//    }
//
//    @Bean
//    public FileWritingMessageHandler fileWriter() {
//        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("destination"));
//        handler.setExpectReply(false);
//        return handler;
//    }
//
//    @Bean
//    public IntegrationFlow integrationFlow() {
//        return IntegrationFlow.from(fileReader(),
//                        spec -> spec.poller(Pollers.fixedDelay(1000)))
//                .transform(transformer, "transform")
//                .handle(fileWriter()).get();
//    }
//}
