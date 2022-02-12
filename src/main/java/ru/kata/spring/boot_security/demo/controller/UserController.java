package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/user")

    public String getUsers(Model model) {
        List<User> userlist = userService.users();
        model.addAttribute("keyValue", userlist);
        return "admin";
    }

    @GetMapping("/admin/user/new")
    public String newUser (Model model) {
        Set<Role> rolesList = roleService.getAllRoles();
        System.out.println(rolesList);
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("rolesList", rolesList);
        return "new";
    }

    @PostMapping("/admin/user")
    public String saveUser(@ModelAttribute User user,
                           @RequestParam(value = "checked", required = false ) Long[] checked){
        if (checked == null) {
            user.setOneRole(roleService.getRoleByName("USER"));
        } else {
            for (Long ong : checked) {
                if (ong != null) {
                    user.setOneRole(roleService.getRoleByID(ong));
                }
            }
        }
        userService.add(user);
        return "redirect:/admin/user/";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        User user = userService.getUserById(id);
        Set <Role> rolesList = roleService.getAllRoles();
        model.addAttribute("rolesList", rolesList);
        model.addAttribute("user", user);
        return "edit";
    }

    @PatchMapping("/admin/user/{id}")
    public String updateUser(@ModelAttribute User user,
                             @RequestParam(value = "checked", required = false ) Long[] checked) {
        if (checked == null) {
            user.setOneRole(roleService.getRoleByName("USER"));
            userService.edit(user);
        } else {
            for (Long ong : checked) {
                if (ong != null) {
                    user.setOneRole(roleService.getRoleByID(ong));
                    userService.edit(user);
                }
            }
        }
        return "redirect:/admin/user/";
    }

    @GetMapping("/admin/user/{id}/delete")
    public String delete(Model model, @PathVariable("id") long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "delete";
    }

    @DeleteMapping("/admin/user/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/user/";
    }


    @GetMapping("/user")
    public String pageForUsers(@AuthenticationPrincipal UserDetails userDetails,
                               Model model) {
        String name = userDetails.getUsername();
        User user = userService.getByName(name);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String pageRedirect() {
        return "redirect:/admin/user/";
    }

}
