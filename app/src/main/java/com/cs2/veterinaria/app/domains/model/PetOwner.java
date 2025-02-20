package com.cs2.veterinaria.app.domains.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PetOwner extends Person{
    private long idOwner;
    private List<Pet> pets = new ArrayList<>();
}
