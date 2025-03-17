package com.cs2.veterinaria.app.adapters.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.users.entity.UserEntity;
import com.cs2.veterinaria.app.adapters.users.repository.UserRepository;
import com.cs2.veterinaria.app.adapters.persons.entity.PersonEntity;
import com.cs2.veterinaria.app.adapters.persons.repository.PersonRepository;
import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.ports.UserPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Service
public class UserAdapter implements UserPort {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean existUserId(long userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public void saveUser(User user) {
        PersonEntity personEntity = personRepository.findByDocument(user.getDocument());
        UserEntity userEntity = new UserEntity(user, personEntity);
        userRepository.save(userEntity);
        user.setUserId(userEntity.getUserId());
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserName(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        return adapterUser(userEntity);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::adapterUser)
                .collect(Collectors.toList());
    }

    private User adapterUser(UserEntity userEntity) {
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        user.setDocument(userEntity.getPerson().getDocument());
        user.setName(userEntity.getPerson().getName());
        user.setAge(userEntity.getPerson().getAge());
        return user;
    }
}