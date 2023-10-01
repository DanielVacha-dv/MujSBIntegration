package cz.danes.mujsbintegration.tcp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Getter
@RequiredArgsConstructor
@Configuration
public class DefaultsProperties {

    private final int port = 12345;

}
