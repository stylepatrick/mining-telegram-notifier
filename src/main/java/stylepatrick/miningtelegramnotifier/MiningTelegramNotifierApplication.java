package stylepatrick.miningtelegramnotifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MiningTelegramNotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiningTelegramNotifierApplication.class, args);
    }

}
