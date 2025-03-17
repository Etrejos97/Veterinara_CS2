package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.PetOwner;

import java.util.List;

public interface PetOwnerPort {
    boolean existPetOwner(long document);
    void savePetOwner(PetOwner petOwner);
    PetOwner findByDocument(long document);
    void deletePetOwner(long document);
    List<PetOwner> findAllPetOwners();
}
