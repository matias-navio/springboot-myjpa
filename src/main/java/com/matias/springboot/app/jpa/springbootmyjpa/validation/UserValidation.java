package com.matias.springboot.app.jpa.springbootmyjpa.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;

@Component
public class UserValidation implements Validator{

    private String MESSAGE_STRING = "no puede ser nulo!";

    @Override
    public boolean supports(@SuppressWarnings("null") Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
       User user = (User)target;

       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, MESSAGE_STRING);
       

       if(user.getLastName() == null || user.getLastName().isBlank()){
            errors.rejectValue("lastName", null, MESSAGE_STRING);
       }
       if(user.getMail() == null || user.getMail().isBlank()){
            errors.rejectValue("mail", null, MESSAGE_STRING);
        }
        if(user.getName() == null || user.getName().isBlank()){
            errors.rejectValue("name", null, MESSAGE_STRING);
       }

    }

}
