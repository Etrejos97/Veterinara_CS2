package com.cs2.veterinaria.app.adapters.historyClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs2.veterinaria.app.adapters.historyClinic.entity.HistoryClinicEntity;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;

public interface HistoryClinicRepository extends JpaRepository<HistoryClinicEntity, Long> {
    HistoryClinic findById(long idHistory);
}
