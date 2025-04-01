package com.cs2.veterinaria.app.domains.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Order {
    private long idOrder;
    private long idPet;
    private long idOwner;
    private long userId;
    private String dateOrder;
    private String drugName;
}
