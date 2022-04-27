package com.example.kkkezdesy.controller;

import com.example.kkkezdesy.entities.User;
import com.example.kkkezdesy.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MainController mainController;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/update")
    public String updateProfile(@RequestBody User user){
        int x = mainController.userIsValid(user);
        User user2 = userRepo.getOne(user.getId());
        if(x == 0 || x == 1){
            user2.setFirst_name(user.getFirst_name());
            user2.setLast_name(user.getLast_name());
            user2.setAge(user.getAge());
            user2.setPassword(passwordEncoder.encode(user.getPassword()));
            user2.setGender(user.getGender());
            user2.setCity(user.getCity());

            userRepo.save(user2);
        }else{
            return "Some field is incorrect.";
        }

        return "User updated.";
    }

    @PostMapping("/delete")
    public String updateProfile(@RequestParam Long id){
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return "User was deleted.";
        }else{
            return "User don't exist.";
        }
    }
}
