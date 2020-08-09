package kganesh1795.security_tutorial.repository;

import org.springframework.data.repository.CrudRepository;

import kganesh1795.security_tutorial.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {
	public Admin findByUsername(String username);
}