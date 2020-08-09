package kganesh1795.security_tutorial.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import kganesh1795.security_tutorial.model.Admin;
import kganesh1795.security_tutorial.model.User;

@TestConfiguration
public class SecurityTestConfig {
	@Bean
	public UserDetailsService testUserService() {
		User user = new User("testUser", "password");
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public UserDetailsService testAdminService() {
		Admin user = new Admin("admin", "superadmin");
		return new InMemoryUserDetailsManager(user);
	}
}