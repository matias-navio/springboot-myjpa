package com.matias.springboot.app.jpa.springbootmyjpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
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
import com.matias.springboot.app.jpa.springbootmyjpa.validation.UserValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidation validation;

    @Transactional(readOnly = true)
    @GetMapping("/list")
    public List<User> userList(){

        return userService.findAll();
    }

    @Transactional(readOnly = true)
    @GetMapping("/map/{id}")
    public ResponseEntity<?> user2(@PathVariable Long id){
        Map<String, Object> data = userService.findByIdMap(id);
        if(data.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<?> user(@Valid @PathVariable Long id){
        Optional<User> optionalUser = userService.findOne(id);
        if(optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.orElseThrow());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result){
        validation.validate(user, result);
        if(result.hasFieldErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){
        user.setAdmin(false);
        return save(user, result);
    }

    @Transactional
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id){
        validation.validate(user, result);
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<User> optionalUser = userService.update(id, user);
        if(optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.orElseThrow());
        }

        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<User> optionalUser = userService.deleteById(id);
        if(optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.orElseThrow());
        }
        return ResponseEntity.badRequest().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        // creamos un mapa para almacenar los errores
        Map<String, Object> errors = new HashMap<>(); 
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        // los devolvemos con badRequest, que sería un 400
        return ResponseEntity.badRequest().body(errors);
    }
}
