package com.cs2.veterinaria.app.domains.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Pet {
    private long idPet;
    private String name;
    private Long idOwner;
    private int age;
    private String species;
    private String race;
    private String characteristics;
    private float weight;
}
