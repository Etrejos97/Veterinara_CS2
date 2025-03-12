package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.User;

public interface UserPort {
    boolean existUserId(long userId);
    void saveUser(User user);
    void deleteUser(long id);
    User findByUserName(Person person);
}
