package com.cs2.veterinaria.app.adapters.clinicalRegistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs2.veterinaria.app.adapters.clinicalRegistry.entity.ClinicalRegistryEntity;

public interface ClinicalRegistryRepository extends JpaRepository<ClinicalRegistryEntity, Long> {
    ClinicalRegistryEntity findByIdRegistry(Long idRegistry);
}
