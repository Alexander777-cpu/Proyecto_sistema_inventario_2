package pe.edu.upeu.msaccesorios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsAccesoriosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAccesoriosApplication.class, args);
    }

}
