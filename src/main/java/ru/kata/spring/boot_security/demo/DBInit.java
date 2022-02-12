package ru.kata.spring.boot_security.demo;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;

@Component
public class DBInit {

    private final UserService userService;
    private final RoleService roleService;

    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void addUsersInDB() {
        User admin = new User("dimas", "fadeev", 28, "111");
        User user = new User("bob", "johnson", 35, "222");
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        roleService.addRole(adminRole);
        roleService.addRole(userRole);
        admin.setOneRole(adminRole);
        user.setOneRole(userRole);
        userService.add(admin);
        userService.add(user);
    }
}
