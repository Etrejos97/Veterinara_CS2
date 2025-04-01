package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;

import java.util.List;

public interface ClinicalPort {
    boolean existHistoryId(long idHistory);
    void saveHistory(HistoryClinic history);
    HistoryClinic createHistory(HistoryClinic history);
    List<HistoryClinic> findClinicalHistoryByPetId(PetEntity pet);
}

