package com.cs2.veterinaria.app.adapters.petOwner.entity;

import com.cs2.veterinaria.app.adapters.persons.entity.PersonEntity;
import com.cs2.veterinaria.app.domains.model.PetOwner;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pet_owner")
@Setter
@Getter
@NoArgsConstructor
public class PetOwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private long idOwner;

    @JoinColumn(name="person_id")
    @OneToOne
    private PersonEntity person;

    public PetOwnerEntity(PetOwner petOwner,  PersonEntity personEntity) {
        this.idOwner = petOwner.getIdOwner();
        this.person = personEntity;
    }
}
