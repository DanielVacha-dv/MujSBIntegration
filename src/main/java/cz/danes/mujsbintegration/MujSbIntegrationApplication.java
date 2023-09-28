package cz.danes.mujsbintegration;

import cz.danes.mujsbintegration.mysupplier.MujSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.http.config.EnableIntegrationGraphController;

import java.util.Scanner;

@EnableIntegrationGraphController
@SpringBootApplication
@Slf4j
public class MujSbIntegrationApplication implements CommandLineRunner {

    @Autowired
    MujSupplier mujSupplier;

    @Override
    public void run(String... args) throws Exception {
        log.info("Application Started !!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter q and press <enter> to exit the program: ");
        while (true) {
            String input = scanner.nextLine();
            mujSupplier.setMessage(input);
            if ("q".equals(input.trim())) {
                break;
            }
        }
        System.out.print("end ");
    }

    public static void main(String[] args) {
        SpringApplication.run(MujSbIntegrationApplication.class, args);
    }

}
