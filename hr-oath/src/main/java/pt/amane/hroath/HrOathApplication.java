package pt.amane.hroath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HrOathApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrOathApplication.class, args);
	}

}
