package cz.danes.mujsbintegration.tcp;

public class MessageDeserializingException extends RuntimeException{

    public MessageDeserializingException() {
        super("Error while deserializing message");
    }
}
