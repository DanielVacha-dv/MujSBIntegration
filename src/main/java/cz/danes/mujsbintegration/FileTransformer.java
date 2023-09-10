package cz.danes.mujsbintegration;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileTransformer {
    public String transform(String filePath) throws IOException {
        String s = new String(Files.readAllBytes(Paths.get(filePath)));
        return "Transform string" + s;
    }
}
