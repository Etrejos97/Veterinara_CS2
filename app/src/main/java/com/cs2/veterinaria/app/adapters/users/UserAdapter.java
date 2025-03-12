package com.cs2.veterinaria.app.adapters.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.persons.entity.PersonEntity;
import com.cs2.veterinaria.app.adapters.users.entity.UserEntity;
import com.cs2.veterinaria.app.adapters.users.repository.UserRepository;
import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.ports.UserPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class UserAdapter implements UserPort{

	@Autowired
    private UserRepository userRepository;
    @Override
    public void deleteUser(long userId) {
        
    }

    @Override
    public boolean existUserId(long userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public User findByUserName(Person person) {
        PersonEntity personEntity = personEntityAdapter(person);
        UserEntity userEntity = userRepository.findByPerson(personEntity);
		User user = userAdapter(userEntity);
        return user;
    }

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userEntityAdapter(user);
		userRepository.save(userEntity);
		user.setUserId(userEntity.getUserId());
        
    }

    private User userAdapter(UserEntity userEntity) {
		if (userEntity == null) {
			return null;
		}
		User user = new User();
		user.setPersonId(userEntity.getPerson().getPersonId());
		user.setDocument(userEntity.getPerson().getDocument());
		user.setName(userEntity.getPerson().getName());
		user.setAge(userEntity.getPerson().getAge());
		user.setUserName(userEntity.getUserName());
		user.setPassword(userEntity.getPassword());
		user.setRole(userEntity.getRole());
		user.setUserId(userEntity.getUserId());
		return user;
		
	}

    private UserEntity userEntityAdapter(User user) {
		PersonEntity personEntity = personEntityAdapter(user);
		UserEntity userEntity = new UserEntity();
		userEntity.setPerson(personEntity);
		userEntity.setUserName(user.getUserName());
		userEntity.setPassword(user.getPassword());
		userEntity.setRole(user.getRole());
		return userEntity;
	}

    private PersonEntity personEntityAdapter(Person person) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setPersonId(person.getPersonId());
		personEntity.setDocument(person.getDocument());
		personEntity.setName(person.getName());
		personEntity.setAge(person.getAge());
		return personEntity;
	}

}
