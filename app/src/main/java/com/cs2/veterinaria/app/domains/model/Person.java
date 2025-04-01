package com.cs2.veterinaria.app.domains.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Person {
    private long personId;
    private long document;
    private String name;
    private int age;
}
