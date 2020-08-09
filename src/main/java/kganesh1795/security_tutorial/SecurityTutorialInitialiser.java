package kganesh1795.security_tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kganesh1795.security_tutorial.model.Admin;
import kganesh1795.security_tutorial.model.User;
import kganesh1795.security_tutorial.service.AdminService;
import kganesh1795.security_tutorial.service.UserService;

@Component
public class SecurityTutorialInitialiser {

	@EventListener(ContextRefreshedEvent.class)
	public void databaseInitialisation(ContextRefreshedEvent e) {
		UserService us = e.getApplicationContext().getBean(UserService.class);
		AdminService as = e.getApplicationContext().getBean(AdminService.class);
		
		try {
			us.loadUserByUsername("testUser");
		}
		catch(UsernameNotFoundException ue) {
			User u = new User("testUser", "password", "Test", "User");
			us.saveUser(u);
		}
		
		try {
			as.loadUserByUsername("admin");
		}
		catch(UsernameNotFoundException ue) {
			Admin a = new Admin("admin", "superadmin");
			as.saveAdmin(a);
		}
	}
}
