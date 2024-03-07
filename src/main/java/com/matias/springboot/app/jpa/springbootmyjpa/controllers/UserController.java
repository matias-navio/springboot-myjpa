package com.matias.springboot.app.jpa.springbootmyjpa.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(readOnly = true)
    @GetMapping("/users")
    public List<User> userList(){

        return userService.findAll();
    }

    @Transactional(readOnly = true)
    @GetMapping("/user/{id}")
    public Map<String, Object> user(@PathVariable Long id){

        return userService.findById(id);
    }

    @Transactional(readOnly = true)
    @GetMapping("/user2/{id}")
    public Optional<User> user2(@PathVariable Long id){

        return userService.findOne(id);
    }

    // @Transactional(readOnly = true)
    // @GetMapping("/mail")
    // public User user(){
    //     String mail = "matias@gmail.com"; 

    //     return userService.findByMail(mail);
    // }

    @Transactional
    @PostMapping("/create")
    public User createUser(@RequestBody User user){

        return userService.save(user);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){

        userService.deleteById(id);
    }

    @Transactional
    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id,@RequestBody User user){

        return userService.save(user);
    }
}
