package com.cs2.veterinaria.app.domains.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class User extends Person{
    private long userId;
    private String user;
    private String password;
    private String role;
}
