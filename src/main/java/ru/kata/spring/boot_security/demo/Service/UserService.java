package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> users();

    void add(User user);

    void edit(User user);

    User getUserById(Long id);

    void delete(Long id);

    User getByName(String name);
}
