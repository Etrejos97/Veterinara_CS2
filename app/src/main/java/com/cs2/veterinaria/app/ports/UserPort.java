package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.User;

import java.util.List;

public interface UserPort {
    boolean existUserId(long userId);
    void saveUser(User user);
    void deleteUser(long id);
    User findByUserName(String userName);
    List<User> findAllUsers();
    User findUserById(long userId);
    List<User> getAll();
}
