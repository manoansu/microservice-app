package pt.amane.hroauth.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * O acesso desse dado é soó na memoria e nao vai aceder banco de dado..
 * 
 * @author manoansu
 *
 */
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private String password;

	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(Long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
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

	public Set<Role> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// converte cada elemento do tio role para GrantedAuthority.
		//SimpleGrantedAuthority => é a classe q implementa a interface GrantedAuthority
		return roles.stream().map(x -> new SimpleGrantedAuthority(x.getRoleName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		// apenas retorna o email para pegar user..
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// Como nao vou implementar essa funcionalidade vou retornar true.
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Como nao vou implementar essa funcionalidade vou retornar true.
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Como nao vou implementar essa funcionalidade vou retornar true.
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Como nao vou implementar essa funcionalidade vou retornar true.
		return true;
	}

}
