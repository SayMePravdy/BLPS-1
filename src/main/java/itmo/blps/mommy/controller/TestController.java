package itmo.blps.mommy.controller;

import itmo.blps.mommy.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    private final EmailService emailService;

    @GetMapping("/test")
    public String test() {
        emailService.send("mikeso3086@vootin.com", "TEST SUBJECT", "TEST FROM SPRING");
        return "Success!";
    }
}
