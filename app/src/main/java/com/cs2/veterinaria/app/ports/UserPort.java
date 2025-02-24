package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.User;

public interface UserPort {
    boolean existUserName(String userName);
    void saveUser(User user);
    void deleteUser(String userName);
    User findByUserName(Person person);
}
