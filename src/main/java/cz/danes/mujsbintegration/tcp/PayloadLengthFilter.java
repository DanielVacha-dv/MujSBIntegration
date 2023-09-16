package cz.danes.mujsbintegration.tcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
public class PayloadLengthFilter implements MessageSelector {

    private final int maxPayloadLength;

    public PayloadLengthFilter(int maxPayloadLength) {
        this.maxPayloadLength = maxPayloadLength;
    }

    @Override
    public boolean accept(Message<?> message) {
        Object payload = message.getPayload();
        log.info(" message header " + getDateTimeFromTimestamp(message.getHeaders().getTimestamp()));
//        if (payload instanceof byte[]) {
//            byte[] payloadBytes = (byte[]) payload;
//            return payloadBytes.length > maxPayloadLength;
//        }
        return true;
      // return false; // Pokud náklad není byte[], tak ho neprocházíme
    }

    public static LocalDateTime getDateTimeFromTimestamp(long timestamp) {
        if (timestamp == 0)
            return null;
        log.info(" long je " + timestamp);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("Europe/Prague"));
    }
}
