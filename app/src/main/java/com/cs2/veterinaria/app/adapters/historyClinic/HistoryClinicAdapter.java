package com.cs2.veterinaria.app.adapters.historyClinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.historyClinic.entity.HistoryClinicEntity;
import com.cs2.veterinaria.app.adapters.historyClinic.repository.HistoryClinicRepository;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;
import com.cs2.veterinaria.app.ports.HistoryClinicPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor
public class HistoryClinicAdapter implements HistoryClinicPort {
    @Autowired
    private HistoryClinicRepository historyRepository;

    @Override
    public boolean existHistory(Long idHistory) {
        return historyRepository.existsById(idHistory); // Llamada correcta al método no estático
    }

    @Override
    public void saveHistory(HistoryClinic history) {
        HistoryClinicEntity historyEntity = new HistoryClinicEntity(history);
        historyRepository.save(historyEntity);
        history.setIdHistory(historyEntity.getIdHistory());
    }

    public HistoryClinic createHistory(HistoryClinic history) {
        HistoryClinicEntity historyEntity = new HistoryClinicEntity(history);
        historyRepository.save(historyEntity);
        return adapterHistory(historyEntity);
    }

    private HistoryClinic adapterHistory(HistoryClinicEntity historyEntity) {
        HistoryClinic history = new HistoryClinic();
        history.setIdHistory(historyEntity.getIdHistory());
        history.setIdPet(historyEntity.getIdPet());
        history.setRecord(historyEntity.getRecord());
        return history;
    }
}
