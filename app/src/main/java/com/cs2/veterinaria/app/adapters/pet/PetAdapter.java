package com.cs2.veterinaria.app.adapters.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.persons.entity.PersonEntity;
import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;
import com.cs2.veterinaria.app.adapters.pet.repository.PetRepository;
import com.cs2.veterinaria.app.adapters.petOwner.entity.PetOwnerEntity;
import com.cs2.veterinaria.app.adapters.petOwner.repository.PetOwnerRepository;
import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.Pet;
import com.cs2.veterinaria.app.ports.PetPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Service
public class PetAdapter implements PetPort {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetOwnerRepository petOwnerRepository;


    @Override
    public Pet updatePet(Pet pet) {
        PetEntity petEntity = new PetEntity();
        petRepository.save(petEntity);
        return adapterPet(petEntity);
    }

    @Override
    public void deletePet(Long idPet) {
        petRepository.deleteById(idPet);
    }

    @Override
    public Pet findPetByIdPet(Long idPet) {
        PetEntity petEntity = petRepository.findById(idPet).orElse(null);
        return adapterPet(petEntity);
    }

    @Override
    public boolean existPetByIdPet(Long idPet) {
        return petRepository.existsById(idPet);
    }

    @Override
    public List<Pet> findByOwnerId(Person owner) {
        PersonEntity ownerId = adapterPerson(owner);
        return petRepository.findByOwner(ownerId).stream()
                .map(this::adapterPet)
                .collect(Collectors.toList());
    }

    @Override
    public long countByOwnerId(Person owner) {
        PersonEntity ownerEntity = adapterPerson(owner); // Adaptamos el objeto Person a PersonEntity
        return petRepository.countByOwner(ownerEntity); // Usamos el objeto adaptado
    }

    @Override
public Pet createPet(Pet pet) {
    // Recuperar el dueño desde la base de datos
    PetOwnerEntity petOwnerEntity = petOwnerRepository.findById(pet.getIdOwner())
        .orElseThrow(() -> new RuntimeException("No existe un dueño con el ID especificado."));

    // Crear la entidad PetEntity
    PetEntity petEntity = new PetEntity();
    petEntity.setName(pet.getName());
    petEntity.setOwner(petOwnerEntity); // Asignar el dueño recuperado
    petEntity.setAge(pet.getAge());
    petEntity.setSpecies(pet.getSpecies());
    petEntity.setRace(pet.getRace());
    petEntity.setCharacteristics(pet.getCharacteristics());
    petEntity.setWeight(pet.getWeight());

    // Guardar la mascota en la base de datos
    petEntity = petRepository.save(petEntity);

    // Adaptar la entidad PetEntity de vuelta al modelo Pet
    return adapterPet(petEntity);
}
    @SuppressWarnings("unused")
    private PetOwnerEntity adaptPersonToPetOwner(PersonEntity personEntity) {
        PetOwnerEntity petOwnerEntity = new PetOwnerEntity();
        petOwnerEntity.setIdOwner(personEntity.getPersonId()); // Mapear el ID del dueño
        petOwnerEntity.setPerson(personEntity); // Asignar el objeto PersonEntity directamente
        return petOwnerEntity;
    }
    private Pet adapterPet(PetEntity petEntity) {
        Pet pet = new Pet();
        pet.setIdPet(petEntity.getIdPet());
        pet.setName(petEntity.getName());
        pet.setIdOwner(petEntity.getOwner().getIdOwner()); // Extraer el ID del dueño
        pet.setAge(petEntity.getAge());
        pet.setSpecies(petEntity.getSpecies());
        pet.setRace(petEntity.getRace());
        pet.setCharacteristics(petEntity.getCharacteristics());
        pet.setWeight(petEntity.getWeight());
        return pet;
    }

    

    private PersonEntity adapterPerson(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setPersonId(person.getPersonId());
        personEntity.setDocument(person.getDocument());
        personEntity.setName(person.getName());
        personEntity.setAge(person.getAge());
        return personEntity;
    }
}
