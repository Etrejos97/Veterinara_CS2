package com.cs2.veterinaria.app.adapters.petOwner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cs2.veterinaria.app.adapters.petOwner.entity.PetOwnerEntity;

public interface PetOwnerRepository extends JpaRepository<PetOwnerEntity, Long> {
    boolean existsByPersonDocument(long document);
    PetOwnerEntity findByPersonDocument(long document);
    void deleteByPersonDocument(long document);
    boolean existsByIdOwner(long idOwner);
}