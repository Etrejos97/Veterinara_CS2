package com.cs2.veterinaria.app.adapters.historyClinic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.historyClinic.entity.HistoryClinicEntity;
import com.cs2.veterinaria.app.adapters.historyClinic.repository.HistoryClinicRepository;
import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;
import com.cs2.veterinaria.app.ports.ClinicalPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor
public class HistoryClinicAdapter implements ClinicalPort {
    @Autowired
    private HistoryClinicRepository historyRepository;

    @Override
    public boolean existHistoryId(long idHistory) {
        return historyRepository.existsById(idHistory); 
    }

    @Override
    public void saveHistory(HistoryClinic history) {
        HistoryClinicEntity historyEntity = new HistoryClinicEntity();

        // Asignar valores a los campos de la entidad
        PetEntity petEntity = new PetEntity();
        petEntity.setIdPet(history.getIdPet());
        historyEntity.setPet(petEntity);

        // Validar y asignar el campo details
        String details = (history.getDetails() != null && !history.getDetails().isEmpty())
                ? history.getDetails()
                : "Detalles no especificados";
        historyEntity.setDetails(details);

        // Guardar la entidad en la base de datos
        historyRepository.save(historyEntity);

        // Actualizar el ID en el modelo
        history.setIdHistory(historyEntity.getIdHistory());
    }

    @Override
    public HistoryClinic createHistory(HistoryClinic history) {
        // Convertir el modelo a la entidad
        HistoryClinicEntity historyEntity = new HistoryClinicEntity();
        PetEntity petEntity = new PetEntity();
        petEntity.setIdPet(history.getIdPet());
        historyEntity.setPet(petEntity);

        // Validar el campo details
        String details = (history.getDetails() != null && !history.getDetails().isEmpty())
                ? history.getDetails()
                : "Detalles no especificados";
        historyEntity.setDetails(details);

        // Guardar en la base de datos
        historyRepository.save(historyEntity);

        // Convertir la entidad de vuelta al modelo
        return adapterHistory(historyEntity);
    }

    private HistoryClinic adapterHistory(HistoryClinicEntity historyEntity) {
        HistoryClinic history = new HistoryClinic();
        history.setIdHistory(historyEntity.getIdHistory());
        history.setIdPet(historyEntity.getPet().getIdPet());
        history.setDetails(historyEntity.getDetails());
        return history;
    }

    @Override
    public List<HistoryClinic> findClinicalHistoryByPetId(PetEntity petEntity) {
        List<HistoryClinicEntity> historyEntities = historyRepository.findByPet(petEntity);
        return historyEntities.stream()
                .map(this::adapterHistory)
                .collect(Collectors.toList());
    }
}
