package com.cs2.veterinaria.app.adapters.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs2.veterinaria.app.adapters.persons.entity.PersonEntity;
import com.cs2.veterinaria.app.adapters.users.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public boolean existsByUserName(String userName);

	public UserEntity findByPerson(PersonEntity personEntity);
	public UserEntity findByUserName(String userName);

}
