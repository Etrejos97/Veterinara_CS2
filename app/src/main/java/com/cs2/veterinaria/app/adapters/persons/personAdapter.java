package com.cs2.veterinaria.app.adapters.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.persons.entity.PersonEntity;
import com.cs2.veterinaria.app.adapters.persons.repository.PersonRepository;
import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.ports.PersonPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Service
public class personAdapter implements PersonPort {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean existPerson(long id) {
        return personRepository.existsByDocument(id);
    }

    @Override
    public Person findByDocument(long id) {
        PersonEntity personEntity = personRepository.findByDocument(id);
        return adapterPerson(personEntity);
    }

    @Override
    public void savePerson(Person person) {
        PersonEntity personEntity = new PersonEntity(person);
        personEntity = personRepository.save(personEntity); // Recuperar el objeto actualizado
        person.setPersonId(personEntity.getPersonId()); // Asignar el ID generado al objeto Person
    }

    @Override
    public void deletePerson(long document) {
        personRepository.deleteByDocument(document);
    }

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll().stream()
                .map(this::adapterPerson)
                .collect(Collectors.toList());
    }
    private Person adapterPerson(PersonEntity personEntity) {
        Person person = new Person();
        person.setPersonId(personEntity.getPersonId());
        person.setDocument(personEntity.getDocument());
        person.setName(personEntity.getName());
        person.setAge(personEntity.getAge());
        return person;
    }
}