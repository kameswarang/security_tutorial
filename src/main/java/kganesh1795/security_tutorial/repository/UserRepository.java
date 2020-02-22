package kganesh1795.security_tutorial.repository;

import org.springframework.data.repository.CrudRepository;

import kganesh1795.security_tutorial.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}