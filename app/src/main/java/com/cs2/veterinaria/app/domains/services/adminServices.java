package com.cs2.veterinaria.app.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.PetOwner;
import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.ports.PersonPort;
import com.cs2.veterinaria.app.ports.UserPort;
import com.cs2.veterinaria.app.ports.PetPort;
import com.cs2.veterinaria.app.ports.PetOwnerPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
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

    public void registerUser(User user) throws Exception {
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
        if (!userPort.existUserId(userId)) {
            throw new Exception("No existe un usuario con ese usuario");
        }
        userPort.deleteUser(userId);
    }

    public List<User> listUsers() {
        return userPort.findAllUsers();
    }

    public void updateUser(User user) throws Exception {
        if (!userPort.existUserId(user.getUserId())) {
            throw new Exception("No existe un usuario con ese usuario");
        }
        userPort.saveUser(user);
    }

    public void registerOwner(Person person) throws Exception {
        if (personPort.existPerson(person.getDocument())) {
            throw new Exception("Ya existe una persona con ese documento");
        }
        personPort.savePerson(person);
    }

    public void deleteOwner(Long document) throws Exception {
        if (!personPort.existPerson(document)) {
            throw new Exception("No existe una persona con ese documento");
        }
        personPort.deletePerson(document);
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
    public long countPetsByOwnerId(long ownerId) {
        return petPort.countByOwnerId(ownerId);
    }

   
}
