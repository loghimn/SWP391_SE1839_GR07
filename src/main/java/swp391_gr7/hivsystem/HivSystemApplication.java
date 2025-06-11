package swp391_gr7.hivsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class HivSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(HivSystemApplication.class, args);
	}

}
