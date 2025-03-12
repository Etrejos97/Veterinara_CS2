package com.cs2.veterinaria.app.domains.model;

import java.util.ArrayList;
import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HistoryClinic {
    private Long idHistory;
    private Long idPet;
    private List<ClinicalRegistry> record = new ArrayList<>();
}
