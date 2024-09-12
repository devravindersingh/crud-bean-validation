package com.ravinder.project.simple.crud.controller;

import com.ravinder.project.simple.crud.model.User;
import com.ravinder.project.simple.crud.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.get());
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/id")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updateUser){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            user.setPassword(updateUser.getPassword());
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }


}
