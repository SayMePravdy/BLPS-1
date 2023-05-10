package itmo.blps.mommy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
public class TestConfig {
    @Value("${owner.name}")
    private String name;
}
