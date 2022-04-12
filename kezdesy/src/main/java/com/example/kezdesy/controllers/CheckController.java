package com.example.kezdesy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ResponseBody
public class CheckController {

    @GetMapping("/home")
    public String check(){
        return "Hello world!";
    }
}
