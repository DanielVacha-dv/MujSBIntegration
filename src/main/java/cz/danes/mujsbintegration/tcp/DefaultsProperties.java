package cz.danes.mujsbintegration.tcp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Getter
@RequiredArgsConstructor
@Configuration
public class DefaultsProperties {

    private int port = 21151;
//    private final TcpServerProperties tcpServer;
//    private final SvakJmsProperties svakMbJms;
//    private final SvakJmsProperties svakKvJms;

//    @Getter
//    @RequiredArgsConstructor
//    public static class TcpServerProperties {
//        private int port = 21151;
//
//        public TcpServerProperties(int i) {
//            port = i;
//        }
//    }
//
//    @Bean
//    public TcpServerProperties tcpServerProperties() {
//        return new TcpServerProperties(21151);
//    }
}
