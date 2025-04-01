package com.cs2.veterinaria.app.adapters.inputs.utils;


public class PetValidator extends SimpleValidator {

    public boolean validatePetID(String petID) {
        return petID != null && !petID.trim().isEmpty();
    }

    public boolean validateOwnerID(String ownerID) {

        return ownerID != null && !ownerID.trim().isEmpty();
    }

    public long validatePetAge(String value) throws Exception {
        return longValidator(value, "edad de la mascota");
    }

    public long validateWeight(String value) throws Exception {
        return longValidator(value, "medida de la mascota");
    }
}
