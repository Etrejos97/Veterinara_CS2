package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.Person;

import java.util.List;

public interface PersonPort {
    boolean existPerson(long document);
    void savePerson(Person person);
    Person findByDocument(long id);
    void deletePerson(long document);
    List<Person> findAllPersons();
}
