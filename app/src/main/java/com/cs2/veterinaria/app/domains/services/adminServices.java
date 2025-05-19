package com.cs2.veterinaria.app.domains.services;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.rest.utils.UserValidator;
import com.cs2.veterinaria.app.Exceptions.BusinessException;
import com.cs2.veterinaria.app.Exceptions.NotFoundException;
import com.cs2.veterinaria.app.adapters.rest.utils.PersonValidator;
import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.PetOwner;
import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.ports.PersonPort;
import com.cs2.veterinaria.app.ports.UserPort;

import jakarta.transaction.Transactional;

import com.cs2.veterinaria.app.ports.PetPort;
import com.cs2.veterinaria.app.ports.PetOwnerPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
// @NoArgsConstructor
@Service
public class adminServices {
    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;
    @Autowired
    private PetPort petPort;
    @Autowired
    private PetOwnerPort petOwnerPort;
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private UserValidator userValidator;
    

    
    public void registerUser(User user) throws Exception {
        if (personPort.existPerson(user.getDocument())) {
            throw new BusinessException("Ya existe una persona con ese documento");
        }
        if (userPort.existUserId(user.getUserId())) {
            throw new BusinessException("Ya existe ese usario registrado");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
    }

    public void updateUser(User user) throws Exception {
        // Buscar el usuario existente por su ID
        User existingUser = userPort.findUserById(user.getUserId());
        if (existingUser == null) {
            throw new Exception("No existe un usuario con el ID especificado.");
        }
    
        // Actualizar los campos permitidos
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
    
        // Guardar los cambios
        userPort.saveUser(existingUser);
    }

    @Transactional
    public void deleteUser(Long userId) throws Exception {
        User user = userPort.findUserById(userId);
        if (user == null) {
            throw new Exception("No existe un usuario con ese ID");
        }
        
        userPort.deleteUser(userId);
        personPort.deletePerson(user.getDocument());
    }

    public List<User> getUsers() throws Exception {
        List<User> users = userPort.getAll();
        if(users.isEmpty()) {
            throw new NotFoundException("No hay usuarios registrados");
        }
        return users;
    }

    public List<Person> listOwners() {
        return personPort.findAllPersons();
    }

    // Actualizar informacion del dueño de la mascota.
    public void updateOwner(PetOwner petOwner) throws Exception {
        if (!petOwnerPort.existPetOwner(petOwner.getDocument())) {
            throw new Exception("No existe una persona con ese documento");
        }
        petOwnerPort.savePetOwner(petOwner);
    }

    // Contar el número de mascotas de un dueño de mascota.
    public long countPetsByOwnerId(Person owner) {
        return petPort.countByOwnerId(owner);
    }

   
}
