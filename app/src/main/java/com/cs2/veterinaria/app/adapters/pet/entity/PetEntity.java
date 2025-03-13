package com.cs2.veterinaria.app.adapters.pet.entity;

import com.cs2.veterinaria.app.domains.model.Pet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pet")
@Setter
@Getter
@NoArgsConstructor
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long idPet;
    @Column(name = "name")
    private String name;
    @Column(name = "id_owner")
    private Long idOwner;
    @Column(name = "age")
    private int age;
    @Column(name = "species")
    private String species;
    @Column(name = "race")
    private String race;
    @Column(name = "characteristics")
    private String characteristics;
    @Column(name = "weight")
    private float weight;

    public PetEntity(Pet pet) {
        this.idPet = pet.getIdPet();
        this.name = pet.getName();
        this.idOwner = pet.getIdOwner();
        this.age = pet.getAge();
        this.species = pet.getSpecies();
        this.race = pet.getRace();
        this.characteristics = pet.getCharacteristics();
        this.weight = pet.getWeight();
    }
}
