package pt.amane.hruser.dtos;

import java.io.Serializable;

import pt.amane.hruser.entities.Role;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String roleName;

	public RoleDTO() {
	}

	public RoleDTO(Long id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
	
	public RoleDTO(Role role) {
		id = role.getId();
		roleName = role.getRoleName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
