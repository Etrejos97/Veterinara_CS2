package com.cs2.veterinaria.app.domains.services;

import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.ports.PersonPort;
import com.cs2.veterinaria.app.ports.UserPort;

public class adminServices {
    private PersonPort personPort;
    private UserPort userPort;

    public void registerUser(User user) throws Exception {
        // Verificar si ya existe una persona con ese documento, si el nombre de
        // usuario ya esta registrado, guardar la informacion
        // de persona y de usuario
        if (personPort.existPerson(user.getDocument())) {
            throw new Exception("Ya existe una persona con ese documento");
        }
        if (userPort.existUserName(user.getUserName())) {
            throw new Exception("Ya existe ese username registrado");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
    }

    public void deleteUser(String userName) throws Exception {
        // Verificar si el usuario existe
        // Eliminar el usuario
        if (!userPort.existUserName(userName)) {
            throw new Exception("No existe un usuario con ese username");
        }
        userPort.deleteUser(userName);
        
    }
}
