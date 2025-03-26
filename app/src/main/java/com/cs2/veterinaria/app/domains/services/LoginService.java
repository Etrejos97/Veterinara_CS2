package com.cs2.veterinaria.app.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.ports.UserPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class LoginService {
    @Autowired
    private UserPort userPort;

    public User login(User user) throws Exception {
        User userValidate = userPort.findByUserName(user.getUserName());
        
        if (userValidate == null) {
            throw new Exception("Usuario o contraseña inválido");
        }

        if (!user.getPassword().equals(userValidate.getPassword())) {
            throw new Exception("Usuario o contraseña inválido");
        }
        
        return userValidate;
    }
}