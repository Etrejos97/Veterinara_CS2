package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.Person;

public interface PersonPort {
    boolean existPerson(long document);
    void savePerson(Person person);
    Person findByDocument(long id);
}
