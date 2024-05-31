package com.matias.springboot.app.jpa.springbootmyjpa.exception;

public class UserListNotFoundException extends RuntimeException{

    public UserListNotFoundException(String message) {
        super(message);
    }
}
