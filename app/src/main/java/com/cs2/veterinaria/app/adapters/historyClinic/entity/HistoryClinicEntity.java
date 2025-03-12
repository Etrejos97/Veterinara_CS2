package com.cs2.veterinaria.app.adapters.historyClinic.entity;

import java.util.ArrayList;
import java.util.List;

import com.cs2.veterinaria.app.domains.model.ClinicalRegistry;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "history_clinical")
@Setter
@Getter
@NoArgsConstructor
public class HistoryClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private Long idHistory;
    @Column(name = "id_pet")
    private Long idPet;

    @OneToMany(mappedBy = "historyClinic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClinicalRegistry> record = new ArrayList<>();

    public HistoryClinicEntity(HistoryClinic history) {
        this.idPet = history.getIdPet();
        this.record.addAll(history.getRecord());
    }
}
