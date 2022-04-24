package com.example.kkkezdesy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthController {

    @GetMapping("/auth")
    public String getAuth(){
        return "authen";
    }
}
