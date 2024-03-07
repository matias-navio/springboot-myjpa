package com.matias.springboot.app.jpa.springbootmyjpa.models.services;

import java.util.List;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;

public interface IUserService {

    public User findOne(Long id);
    public List<User> findAll();
    public User save(User user);
    public void deleteById(Long id);
}
