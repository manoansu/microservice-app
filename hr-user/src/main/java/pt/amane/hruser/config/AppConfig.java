package pt.amane.hruser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {
	
	/**
	 * cria se uma unca instancia BCryptPasswordEncoder
	 * para poder aceder o encode para gerar o hash de um string
	 * que user digitou para criptografar a senha.
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
