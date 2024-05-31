package com.matias.springboot.app.jpa.springbootmyjpa.controllers;


import com.matias.springboot.app.jpa.springbootmyjpa.exception.UserNotFoundException;
import com.matias.springboot.app.jpa.springbootmyjpa.exception.dto.Error;
import com.matias.springboot.app.jpa.springbootmyjpa.exception.UserListNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

@RestControllerAdvice
public class HandlerExceptionController {

    private Error error;

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> handlerNotFound(NoHandlerFoundException e){

        error = new Error(e.getMessage(), "La ruta no se encontró", HttpStatus.NOT_FOUND, new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> userNotFound(UserNotFoundException e){
        error = new Error(e.getMessage(), "El usuario no está en la DB", HttpStatus.FORBIDDEN, new Date());
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(UserListNotFoundException.class)
    public ResponseEntity<Error> userListNotFound(UserListNotFoundException e){
        error = new Error(e.getMessage(), "la lista de usuarios esta vacía", HttpStatus.BAD_REQUEST, new Date());
        return ResponseEntity.status(400).body(error);
    }

}
