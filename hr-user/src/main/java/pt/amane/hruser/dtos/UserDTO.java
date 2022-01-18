package pt.amane.hruser.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import pt.amane.hruser.entities.Role;
import pt.amane.hruser.entities.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private String password;

	private Set<RoleDTO> roles = new HashSet<>();

	public UserDTO() {
	}

	public UserDTO(Long id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		password = user.getPassword();
	}

	public UserDTO(User user, Set<Role> obj) {
		this(user);
		obj.forEach(x -> roles.add(new RoleDTO(x)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

}
