package cz.danes.mujsbintegration.tcp;

public interface TcpMessageReceived {
    String TCP_MESSAGE_RECEIVED_SUBSCRIBABLE_CHANNEL_NAME = "car-received-subscribable-channel-name";

    void onCarReceivedEvent(String message);
}
