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
import lombok.Setter;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "user")
public class User implements UserDetails {

	private static final long serialVersionUID = 3307834320580557527L;

	public User(String u, String p, String f, String l) {
		this.username = u;
		this.password = p;
		this.firstName = f;
		this.lastName = l;
	}
	
	@Id
	@NonNull
	@NotBlank(message = "Provide a username atleast 5 characters long")
	@Size(min = 5, message = "Must be atleast five characters long")
	private String username;

	@NonNull
	@Setter
	@NotBlank(message = "Provide a password atleast 5 characters long")
	@Size(min = 5, message = "Must be atleast five characters long")
	private String password;

	@NotBlank(message = "Provide a name atleast 2 characters long")
	@Size(min = 2, message = "Must be atleast two characters long")
	private String firstName;

	@NotBlank(message = "Provide a name atleast 2 characters long")
	@Size(min = 2, message = "Must be atleast two characters long")
	private String lastName;

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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