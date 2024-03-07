package com.matias.springboot.app.jpa.springbootmyjpa.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;
import com.matias.springboot.app.jpa.springbootmyjpa.models.services.IUserService;

@RestController
@RequestMapping("/app")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public Map<String, Object> userList(){

        
        return null;
    }

    @GetMapping("/user/{id}")
    public User user(@PathVariable Long id){
        
        return userService.findOne(id);
    }
}
