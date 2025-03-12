package com.cs2.veterinaria.app.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.ports.PersonPort;
import com.cs2.veterinaria.app.ports.UserPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class adminServices {
    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;

    public void registerUser(User user) throws Exception {
        // Verificar si ya existe una persona con ese documento, si el nombre de
        // usuario ya esta registrado, guardar la informacion
        // de persona y de usuario
        if (personPort.existPerson(user.getDocument())) {
            throw new Exception("Ya existe una persona con ese documento");
        }
        if (userPort.existUserId(user.getUserId())) {
            throw new Exception("Ya existe ese usario registrado");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
    }

    public void deleteUser(Long userId) throws Exception {
        // Verificar si el usuario existe
        // Eliminar el usuario
        if (!userPort.existUserId(userId)) {
            throw new Exception("No existe un usuario con ese usuario");
        }
        userPort.deleteUser(userId);
        
    }
}
