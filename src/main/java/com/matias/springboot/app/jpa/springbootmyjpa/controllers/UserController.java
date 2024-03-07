package com.matias.springboot.app.jpa.springbootmyjpa.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<User> userList(){

        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User user(@PathVariable Long id){
        
        return userService.findOne(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){

        return userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){

        userService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id,@RequestBody User user){

        return userService.save(user);
    }
}
