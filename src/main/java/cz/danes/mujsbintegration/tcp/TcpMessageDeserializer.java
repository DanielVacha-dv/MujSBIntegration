package cz.danes.mujsbintegration.tcp;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.serializer.Deserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class TcpMessageDeserializer implements Deserializer<String> {
    @Override
    public @NonNull String deserialize(@NonNull InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            char[] buffer = new char[1024];
            int buffered = reader.read(buffer);
            if(buffered >= 0) {
                String message = new String(buffer, 0, buffered).trim();
                log.info(". deserialize: Message deserialized: " + message);
                return message;
            } else throw new MessageDeserializingException();
        } catch (IOException e) {
            log.error(". deserialize: Error while consuming message: " + e.getMessage());
            throw new MessageDeserializingException();
        }
    }
}
