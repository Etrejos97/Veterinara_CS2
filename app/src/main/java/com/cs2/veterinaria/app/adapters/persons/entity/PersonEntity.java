package com.cs2.veterinaria.app.adapters.persons.entity;

import com.cs2.veterinaria.app.domains.model.Person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Setter
@Getter
@NoArgsConstructor
public class PersonEntity {
    public PersonEntity(Person person) {
		this.personId=person.getPersonId();
        this.document=person.getDocument();
		this.name=person.getName();
        this.age=person.getAge();
		
	}
	@Id
	@Column(name = "personId")
	private long personId;
    @Column(name = "document")
	private long document;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	
}
