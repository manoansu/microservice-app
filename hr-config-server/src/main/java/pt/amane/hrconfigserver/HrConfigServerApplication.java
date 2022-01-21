package pt.amane.hrconfigserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class HrConfigServerApplication implements CommandLineRunner{

	//Testa o variavel de ambiente local para ver se username esta correta
	@Value("${spring.cloud.config.server.git.username}")
	private String user;
	
	//Testa o variavel de ambiente local para ver se password esta correta
	@Value("${spring.cloud.config.server.git.password}")
	private String password;
	
	public static void main(String[] args) {
		SpringApplication.run(HrConfigServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("USERNAME ==> " + user);
		//System.out.println("\nPASSWORD ==> " + password);
		
	}

}
