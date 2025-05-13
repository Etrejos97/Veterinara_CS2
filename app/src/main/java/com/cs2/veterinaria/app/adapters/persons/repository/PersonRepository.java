package com.cs2.veterinaria.app.adapters.persons.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs2.veterinaria.app.adapters.persons.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    boolean existsByDocument(long document);
    PersonEntity findByDocument(long id);
    void deleteByDocument(long document);
}
