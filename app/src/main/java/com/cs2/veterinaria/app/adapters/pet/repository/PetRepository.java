package com.cs2.veterinaria.app.adapters.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;

public interface PetRepository extends JpaRepository<PetEntity, Long> {
    PetEntity findPetByIdPet(Long id);
}
