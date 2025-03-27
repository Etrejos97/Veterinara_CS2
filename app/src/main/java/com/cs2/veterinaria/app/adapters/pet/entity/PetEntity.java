package com.cs2.veterinaria.app.adapters.pet.entity;

import com.cs2.veterinaria.app.adapters.petOwner.entity.PetOwnerEntity;
import com.cs2.veterinaria.app.domains.model.Pet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @JoinColumn(name = "id_owner")
    @OneToOne
    private PetOwnerEntity owner;

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

    public PetEntity(Pet pet, PetOwnerEntity petOwnerEntity) {
        this.idPet = pet.getIdPet();
        this.name = pet.getName();
        this.owner = petOwnerEntity;
        this.age = pet.getAge();
        this.species = pet.getSpecies();
        this.race = pet.getRace();
        this.characteristics = pet.getCharacteristics();
        this.weight = pet.getWeight();
    }
}
