package lab5.kittyMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "lab5.kittyMicroservice")
public class KittyMicroserviceApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "kitty");
        SpringApplication.run(KittyMicroserviceApplication.class, args);
    }
}
