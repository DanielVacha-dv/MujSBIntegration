package cz.danes.mujsbintegration.tcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/apiv1")
public class GWController {

    @Autowired
    private TcpConfig.TcpGateway tcpGateway;

    @GetMapping(path = "/gwsend")
    @ResponseBody
    public ResponseEntity<String> sendMessage2Tcp(@RequestParam("message") String message) {
        log.info("sendAndReceive message {}", message);
        String s = tcpGateway.sendAndReceive(message);
//        array of string numbers
        String strings = Arrays.stream(s.split(","))
                .map(Integer::parseInt) // Převedení ASCII hodnoty na int
                .map(value -> (char) value.intValue()) // Převedení int na znak
                .map(String::valueOf) // Převedení znaku na řetězec
                .collect(Collectors.joining());
        return new ResponseEntity<>(
                "send and receive " + s + " and s:" + strings, HttpStatus.OK);
    }
}
