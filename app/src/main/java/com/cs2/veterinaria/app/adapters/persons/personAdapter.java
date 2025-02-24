package com.cs2.veterinaria.app.adapters.persons;

import com.cs2.veterinaria.app.adapters.persons.entity.PersonEntity;
import com.cs2.veterinaria.app.adapters.persons.repository.PersonRepository;
import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.ports.PersonPort;

public class personAdapter implements PersonPort{
    private PersonRepository personRepository;
    @Override
    public boolean existPerson(long id) {
        return personRepository.existsById(id);
    }

    @Override
    public Person findByDocument(long id) {
        PersonEntity personEntity = personRepository.findByDocument(id);
		return adapterPerson(personEntity);
    }

    @Override
    public void savePerson(Person person) {
        PersonEntity personEntity = new PersonEntity(person);
		personRepository.save(personEntity);
		person.setDocument(personEntity.getDocument());
        
    }

    private Person adapterPerson(PersonEntity personEntity) {
		Person person= new Person();
		person.setDocument(personEntity.getDocument());
		person.setName(personEntity.getName());
		person.setAge(personEntity.getAge());
		return person;
	}

    
}
