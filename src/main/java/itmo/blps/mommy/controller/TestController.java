package itmo.blps.mommy.controller;

import itmo.blps.mommy.config.TestConfig;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    private TestConfig testConfig;

    @GetMapping("/test")
    public String test() {
        return testConfig.getName();
    }
}
