package com.cs2.veterinaria.app.domains.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Bill {
    private Long idBill;
    private long idOrder;
    private long idPet;
    private long idOwner;
    private String dateCreated;
    private double price;
}
