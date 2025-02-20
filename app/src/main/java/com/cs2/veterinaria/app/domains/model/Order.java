package com.cs2.veterinaria.app.domains.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Order {
    private int idOrder;
    private long idPet;
    private long idOwner;
    private long idVerterinarian;
    private String dateOrder;
    private String drugName;
}
