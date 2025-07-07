package lab5.ownerMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "lab5.ownerMicroservice")
public class OwnerMicroserviceApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "owner");
        SpringApplication.run(OwnerMicroserviceApplication.class, args);
    }
}
