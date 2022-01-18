package pt.amane.hruser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HrUserApplication implements CommandLineRunner{
	
	/**
	 * gera a senha criptografado..
	 */
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HrUserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// testa o codigo criptografado em hash..
		//System.out.println("BCRYPT = " + passwordEncoder.encode("123456"));
	}

}
