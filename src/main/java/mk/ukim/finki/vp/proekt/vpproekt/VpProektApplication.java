package mk.ukim.finki.vp.proekt.vpproekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VpProektApplication {

    public static void main(String[] args) {
        SpringApplication.run(VpProektApplication.class, args);
    }

}
