package tn.esprit.spring.service;

import tn.esprit.spring.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String email;
	private boolean isBlocked;
	private String verificationCode;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetails(Long id, String username, String password, String email, boolean isBlocked,String verificationCode,
					   Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isBlocked = isBlocked;
		this.authorities = authorities;
		this.verificationCode = verificationCode;

	}

	public static UserDetails build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetails(
				user.getId(), 
				user.getUserName(),
				user.getPassword(),
				user.getEmail(),
				user.isBlocked(),
				user.getVerificationCode(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetails user = (UserDetails) o;
		return Objects.equals(id, user.id);
	}
}
