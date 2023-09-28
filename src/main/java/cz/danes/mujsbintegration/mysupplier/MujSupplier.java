package cz.danes.mujsbintegration.mysupplier;

import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Data
@Component
public class MujSupplier implements Supplier<Message<String>> {


    private String message;

    public MujSupplier() {
        message="";
    }

    @Override
    public Message<String> get() {
        return new GenericMessage<>(message);
    }

}
