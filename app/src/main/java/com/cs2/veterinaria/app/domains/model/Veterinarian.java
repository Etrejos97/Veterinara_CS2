package com.cs2.veterinaria.app.domains.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Veterinarian extends Person{
    private long idVerterinarian;
    private String user;
    private String password;
}
