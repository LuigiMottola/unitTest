package com.example.unitTest.services;

import com.example.unitTest.entities.User;
import com.example.unitTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public List<User> getAllUser() {
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

    public Optional<User> getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional;
    }

    public Optional<User> updateUser(Long id,User user){
        Optional<User> userDaAggiornare = userRepository.findById(id);
        if (userDaAggiornare.isPresent()){
            userDaAggiornare.get().setName(user.getName());
            userDaAggiornare.get().setSurname(user.getSurname());
            userRepository.save(userDaAggiornare.get());
        } else {
            return Optional.empty();
        }
        return userDaAggiornare;
    }


    public Optional<User> deleteUser(Long id){
        Optional<User> UserOptional = userRepository.findById(id);
        if (UserOptional.isPresent()){
            userRepository.deleteById(id);
        }
        return UserOptional;
    }
}