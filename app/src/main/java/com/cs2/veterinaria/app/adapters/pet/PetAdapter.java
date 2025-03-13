package com.cs2.veterinaria.app.adapters.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;
import com.cs2.veterinaria.app.adapters.pet.repository.PetRepository;
import com.cs2.veterinaria.app.domains.model.Pet;
import com.cs2.veterinaria.app.ports.PetPort;


@Service
public class PetAdapter implements PetPort {
    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet createPet(Pet pet) {
        PetEntity petEntity = new PetEntity(pet);
        petRepository.save(petEntity);
        pet.setIdPet(petEntity.getIdPet());
        return pet;
    }

    @Override
    public Pet updatePet(Pet pet) {
        PetEntity petEntity = new PetEntity(pet);
        petRepository.save(petEntity);
        return pet;
    }

    @Override
    public void deletePet(Long idPet) {
        petRepository.deleteById(idPet);
    }

    @Override
    public Pet findPetByIdPet(Long idPet) {
        return petRepository.findById(idPet)
                .map(this::adapterPet)
                .orElse(null);
    }

    @Override
    public boolean existPetByIdPet(Long idPet) {
        return petRepository.existsById(idPet);
    }

    private Pet adapterPet(PetEntity petEntity) {
        Pet pet = new Pet();
        pet.setIdPet(petEntity.getIdPet());
        pet.setName(petEntity.getName());
        pet.setIdOwner(petEntity.getIdOwner());
        pet.setAge(petEntity.getAge());
        pet.setSpecies(petEntity.getSpecies());
        pet.setRace(petEntity.getRace());
        pet.setCharacteristics(petEntity.getCharacteristics());
        pet.setWeight(petEntity.getWeight());
        return pet;
    }
}
