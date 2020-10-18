package kganesh1795.security_tutorial.model;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//@Component
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "admin")
public class Admin implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1407393377080476258L;

	@Id
	@NonNull
	@NotBlank(message = "Provide a username atleast 5 characters long")
	@Size(min = 5, message = "Must be atleast five characters long")
	private String username;

	@NonNull
	@NotBlank(message = "Provide a password atleast 5 characters long")
	@Size(min = 5, message = "Must be atleast five characters long")
	private String password;

	public String getName() {
		return "Administrator";
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
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
}