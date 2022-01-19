package pt.amane.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pt.amane.hroauth.dtos.UserDTO;
import pt.amane.hroauth.entities.User;
import pt.amane.hroauth.feignclient.UserFeignClient;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient userFeignClient;

	/**
	 * Metodo de para teste que é chamado 
	 * para verificar se existe ou nao user.
	 * No proficional esse metodo nao é necessario
	 * uma vez que vamos usar spring securite.
	 */
	public User findByEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();

		if (user == null) {
			logger.error("object not found: " + email);
			throw new IllegalArgumentException("Email not found!");
		}
		logger.info("Email found: " + email);
		return user;
	}

	
	/**
	 * Metodo de spring securite que é chamado para verificar se existe ou nao user.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userFeignClient.findByEmail(username).getBody();

		if (user == null) {
			logger.error("object not found: " + username);
			throw new UsernameNotFoundException("Email not found!");
		}
		logger.info("Email found: " + username);
		return user;
	}

}
