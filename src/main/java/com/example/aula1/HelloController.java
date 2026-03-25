package com.example.aula1;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @PostMapping("/saudacao")
    public String saudacao(@RequestBody String name){
        return "Olá " + name+" este é o seu primeiro teste post no postman";
    }
}
