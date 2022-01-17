package pt.amane.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	/**
	 * Method: serve para registar instancia unica 
	 * um singleton de um objeto de RestTemplate,
	 * e essa instancia vai ficar desponivel 
	 * para injectar outros componentes, mas apartir 
	 * de uma chamada de metodo mas na outra classe nao nessa
	 * por isso temos q anotar Bean.
	 * @return
	 */
	@Bean
	public RestTemplate registerRestTemplate() {
		return new RestTemplate();
	}

}
