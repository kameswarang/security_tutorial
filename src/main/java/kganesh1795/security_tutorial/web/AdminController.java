package kganesh1795.security_tutorial.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kganesh1795.security_tutorial.model.Admin;
import kganesh1795.security_tutorial.service.AdminService;

@Controller
@RequestMapping(path = {"/admin"})
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@GetMapping
	public String getHome(Model model, @AuthenticationPrincipal(errorOnInvalidType = true) UserDetails principle) {
		Admin admin = (Admin) adminService.loadUserByUsername(principle.getUsername());
		model.addAttribute("admin", admin);
		return "admin";
	}
}