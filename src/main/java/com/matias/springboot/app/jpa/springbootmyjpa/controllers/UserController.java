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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

        return userService.findByIdMap(id);
    }

    @Transactional(readOnly = true)
    @GetMapping("/user2/{id}")
    public Optional<User> user2(@PathVariable Long id){

        return userService.findOne(id);
    }

    @Transactional(readOnly = true)
    @GetMapping("/mail/{id}")
    public User userMail(@PathVariable Long id){
        String mail = "matias@gmail.com";
        Optional<User> user = userService.findOne(id);

        if(user.get().getMail().equals(mail)){
            return userService.findByMail(mail);
        }
        //manejar una excepcion
        return null;
    }

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

    @Transactional
    @PutMapping("/update-all")
    public List<User> updateAll(@RequestBody List<User> users){

        return userService.saveAll(users);
    }
}
