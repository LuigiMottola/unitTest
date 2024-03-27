package com.example.unitTest.controllers;

import com.example.unitTest.entities.User;
import com.example.unitTest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUsers =   userService.getAllUser();
        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id){
        Optional<User> userOptional = userService.getUser(id);
        return ResponseEntity.ok().body(userOptional);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User user,@PathVariable Long id){
        Optional<User> userOptional = userService.updateUser(id,user);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        Optional<User> userDaCancellare = userService.deleteUser(id);
        return ResponseEntity.ok().body(userDaCancellare.get());
    }




}