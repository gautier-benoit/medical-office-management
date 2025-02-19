package miage.gautier.praticien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PraticienApplication {
    public static void main(String[] args) {
        SpringApplication.run(PraticienApplication.class, args);
    }
}