package cz.danes.mujsbintegration.tcp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class TcpMessageHandler implements GenericHandler<String> {

    @Override
    public String handle(String payload, MessageHeaders headers) {
        log.info(". handle: Data read successfully " + payload);

        String address = headers.get("ip_address", String.class);
        return address;
    }
}
