package cz.danes.mujsbintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MujSbIntegrationApplication {//} implements CommandLineRunner {

//    @Autowired
//    private TcpConfig.TcpGateway tcpGateway;
    public static void main(String[] args) {
        SpringApplication.run(MujSbIntegrationApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//        String response = tcpGateway.sendAndReceive("1 Hello, server!");
//        System.out.println("Response from server: " + response);
//    }
}
