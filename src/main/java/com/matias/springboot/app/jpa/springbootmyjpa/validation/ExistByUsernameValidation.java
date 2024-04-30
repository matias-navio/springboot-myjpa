package com.matias.springboot.app.jpa.springbootmyjpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matias.springboot.app.jpa.springbootmyjpa.models.services.UserServiceImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByUsernameValidation implements ConstraintValidator<ExistByUsername, String>{

    @Autowired
    private UserServiceImpl service;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }

        return !service.existsByUsername(username);
    }

}
