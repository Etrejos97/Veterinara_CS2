package com.cs2.veterinaria.app.adapters.historyClinic.entity;

import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @JoinColumn(name="id_pet")
    @OneToOne
    private PetEntity pet;
    @Column(name = "details")
    private String details;

    public HistoryClinicEntity(Long idHistory, PetEntity petEntity, String details) {
        this.idHistory = idHistory;
        this.pet = petEntity;
        this.details = details;
    }

    
}
