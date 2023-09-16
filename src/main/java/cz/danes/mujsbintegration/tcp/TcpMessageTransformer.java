package cz.danes.mujsbintegration.tcp;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class TcpMessageTransformer {
    public String transform(Object num) throws IOException {
//        String s = new String(Files.readAllBytes(Paths.get(filePath)));
//        System.out.println("num:" + num);
        String s = new String((byte[]) num, StandardCharsets.UTF_8);
//        int i = Integer.parseInt(num.toString());
//        String str = Character.toString((char) i);
        return "Transform string:" + s;
    }
}
