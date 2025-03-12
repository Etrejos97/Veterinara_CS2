package com.cs2.veterinaria.app.adapters.historyClinic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name="id_pet")
    private Long idPet;
    @Column(name = "details")
    private String details;

    
}
