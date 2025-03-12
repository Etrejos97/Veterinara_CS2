package com.cs2.veterinaria.app.domains.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HistoryClinic {
    private Long idHistory;
    private Long idPet;
    private String details;
}
