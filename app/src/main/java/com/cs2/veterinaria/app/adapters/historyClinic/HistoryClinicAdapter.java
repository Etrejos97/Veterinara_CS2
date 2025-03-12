package com.cs2.veterinaria.app.adapters.historyClinic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.historyClinic.entity.HistoryClinicEntity;
import com.cs2.veterinaria.app.adapters.historyClinic.repository.HistoryClinicRepository;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;
import com.cs2.veterinaria.app.ports.ClinicalPort;
import com.cs2.veterinaria.app.ports.HistoryClinicPort;

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
        return historyRepository.existsById(idHistory); // Llamada correcta al método no estático
    }

    @Override
    public void saveHistory(HistoryClinic history) {
        HistoryClinicEntity historyEntity = new HistoryClinicEntity();
        historyRepository.save(historyEntity);
        history.setIdHistory(historyEntity.getIdHistory());
    }

    public HistoryClinic createHistory(HistoryClinic history) {
        HistoryClinicEntity historyEntity = new HistoryClinicEntity();
        historyRepository.save(historyEntity);
        return adapterHistory(historyEntity);
    }

    private HistoryClinic adapterHistory(HistoryClinicEntity historyEntity) {
        HistoryClinic history = new HistoryClinic();
        history.setIdHistory(historyEntity.getIdHistory());
        history.setIdPet(historyEntity.getIdPet());
        history.setDetails(historyEntity.getDetails());
        return history;
    }

    @Override
    public List<HistoryClinic> findClinicalHistoryByPetId(long idPet) {
        List<HistoryClinicEntity> historyEntities = historyRepository.findByIdPet(idPet);
        return historyEntities.stream()
                .map(this::adapterHistory)
                .collect(Collectors.toList());
    }
}
