package com.cs2.veterinaria.app.adapters.pet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;

public interface PetRepository extends JpaRepository<PetEntity, Long> {
    PetEntity findPetByIdPet(Long id);
    List<PetEntity> findByIdOwner(long ownerId);
    long countByIdOwner(long ownerId);
}
