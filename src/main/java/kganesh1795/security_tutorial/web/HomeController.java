package kganesh1795.security_tutorial.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kganesh1795.security_tutorial.service.AdminService;
import kganesh1795.security_tutorial.service.UserService;

@Controller
@RequestMapping(path = {"/home", "/"})
public class HomeController {
	@Autowired
	AdminService adminService;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getHome(Model model, @AuthenticationPrincipal(errorOnInvalidType = true) UserDetails principle) {
		String username = principle.getUsername();
		Object user;
		
		if(username.equals("admin")) {
			user = adminService.loadUserByUsername(username);
		}
		else {
			user = userService.loadUserByUsername(username);
		}
		model.addAttribute("user", user);
		return "home";
	}
}