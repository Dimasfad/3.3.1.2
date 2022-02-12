package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Set<Role> findAll();
    Role findRoleByRole(String role);
    Role findRoleById(Long id);
}
