package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.Pet;

import java.util.List;

public interface PetPort {
    Pet createPet(Pet pet);
    Pet updatePet(Pet pet);
    void deletePet(Long idPet);
    Pet findPetByIdPet(Long idPet);
    boolean existPetByIdPet(Long idPet);
    List<Pet> findByOwnerId(Person owner);
    long countByOwnerId(Person owner);
    
}
