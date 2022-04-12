package com.example.kezdesy.controllers;

import com.example.kezdesy.entities.User;
import com.example.kezdesy.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        if(user != null){
            userRepo.save(user);
            return "User added.";
        }else{
            return "User not inserted";
        }
    }
}
