package com.matias.springboot.app.jpa.springbootmyjpa.models.services;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;

public interface IUserService {

    public User findOne(Long id);

}
