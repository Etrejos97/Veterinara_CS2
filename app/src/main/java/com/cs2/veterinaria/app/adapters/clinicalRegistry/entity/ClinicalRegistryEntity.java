package com.cs2.veterinaria.app.adapters.clinicalRegistry.entity;

import com.cs2.veterinaria.app.domains.model.ClinicalRegistry;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clinical_registry")
@Setter
@Getter
@NoArgsConstructor
public class ClinicalRegistryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registry")
    private Long idRegistry;
    @Column(name = "date_registrer")
    private String dateRegistrer;
    @Column(name = "veterinarian")
    private String veterinarian;
    @Column(name = "rason")
    private String reasonConsultation;
    @Column(name = "diagnosis")
    private String diagnosis;
    @Column(name = "dose")
    private String dose;
    @Column(name = "id_order")
    private int idOrder;

    @ManyToOne
    @JoinColumn(name = "id_history", nullable = false)
    private HistoryClinic historyClinic;

    public ClinicalRegistryEntity( ClinicalRegistry registry) {
        this.idRegistry = registry.getIdRegistry();
        this.dateRegistrer = registry.getDateRegistrer();
        this.veterinarian = registry.getVeterinarian();
        this.reasonConsultation = registry.getReasonConsultation();
        this.diagnosis = registry.getDiagnosis();
        this.dose = registry.getDose();
        this.idOrder = registry.getIdOrder();
    }
}
