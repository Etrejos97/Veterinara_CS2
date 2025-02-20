package com.cs2.veterinaria.app.domains.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClinicalRegistry {
    private String dateRegistrer;
    private String veterinarian;
    private String reasonConsultation;
    private String diagnosis;
    private String dose;
    private int idOrder;
}
