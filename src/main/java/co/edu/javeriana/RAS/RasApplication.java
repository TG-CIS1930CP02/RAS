package co.edu.javeriana.RAS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("co.edu.javeriana.RAS")
@ComponentScan("co.edu.javeriana.RAS")
public class RasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RasApplication.class, args);
	}
	
}
