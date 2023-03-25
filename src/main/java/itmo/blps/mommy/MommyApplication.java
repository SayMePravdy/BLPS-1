package itmo.blps.mommy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class MommyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MommyApplication.class, args);
    }
}
