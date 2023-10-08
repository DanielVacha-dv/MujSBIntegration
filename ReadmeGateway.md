## Simple example with service activators and gateways

TcpConfig2 define mainFlow which read messages from Tcp server listening on port (tcp.server.port)
12345
than message is handle by first ServiceActivator which is object of HandlerEndpoint
and this handler function send this message to object with name "outputChannel"
This channel is connected to @ServiceActivator "consumeStringMessage(String message)"
which resend message to gateway.
@MessagingGateway(defaultRequestChannel = "myInputChannel2")
public interface TcpGateway {
void sendAndReceive(String message);
}
