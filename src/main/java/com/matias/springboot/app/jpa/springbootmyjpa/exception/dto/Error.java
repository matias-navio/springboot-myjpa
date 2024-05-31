package com.matias.springboot.app.jpa.springbootmyjpa.exception.dto;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class Error {

    private String message;
    private String error;
    private HttpStatus status;
    private Date date;

    public Error(String message, String error, HttpStatus status, Date date) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
