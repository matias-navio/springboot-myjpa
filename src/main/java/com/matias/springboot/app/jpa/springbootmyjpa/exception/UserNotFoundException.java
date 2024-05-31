package com.matias.springboot.app.jpa.springbootmyjpa.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
