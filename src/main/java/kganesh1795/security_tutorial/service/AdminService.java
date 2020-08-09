package kganesh1795.security_tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kganesh1795.security_tutorial.model.Admin;
import kganesh1795.security_tutorial.repository.AdminRepository;
import kganesh1795.security_tutorial.service.interfaces.CustomAdminService;

@Service
public class AdminService implements UserDetailsService, CustomAdminService {
	private AdminRepository adminRepo;
	private PasswordEncoder passEnc;

	@Autowired
	public AdminService(AdminRepository ar, PasswordEncoder pe) {
		this.adminRepo = ar;
		this.passEnc = pe;
	}

	@Override
	public void saveAdmin(Admin a) {
		a.setPassword(passEnc.encode(a.getPassword()));
		adminRepo.save(a);
	}

	@Override
	public UserDetails loadUserByUsername(String u) throws UsernameNotFoundException {
		Admin admin = adminRepo.findByUsername(u);
		if (admin != null) {
			return admin;
		} else
			throw new UsernameNotFoundException("Admin User '" + u + "' not found");
	}

}
