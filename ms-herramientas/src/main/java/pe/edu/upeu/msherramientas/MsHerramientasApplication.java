package pe.edu.upeu.msherramientas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsHerramientasApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsHerramientasApplication.class, args);
    }

}
