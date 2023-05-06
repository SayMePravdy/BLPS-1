package itmo.blps.mommy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@Configuration
@EnableJms
@EnableDiscoveryClient
public class MommyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MommyApplication.class, args);
    }
}
