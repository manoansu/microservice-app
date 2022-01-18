package pt.amane.hruser.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.hruser.dtos.UserDTO;
import pt.amane.hruser.entities.User;
import pt.amane.hruser.reositories.UserRepository;
import pt.amane.hruser.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> userId = repository.findById(id);
		User user = userId.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + User.class.getName()));
		return new UserDTO(user, user.getRoles());
	}
	
	@Transactional(readOnly = true)
	public UserDTO findByEmail(String email) {
		User user = repository.findByEmail(email);
		return new UserDTO(user, user.getRoles());
	}
	

}
