package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Set<Role> getAllRoles();
    Role getRoleByID(Long id);
    Role getRoleByName(String name);
    void addRole(Role role);
}
