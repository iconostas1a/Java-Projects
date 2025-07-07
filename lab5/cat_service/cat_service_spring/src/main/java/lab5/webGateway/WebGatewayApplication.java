package lab5.webGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"lab5.webGateway"})
public class WebGatewayApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "gateway");
        SpringApplication.run(WebGatewayApplication.class, args);
    }
}
