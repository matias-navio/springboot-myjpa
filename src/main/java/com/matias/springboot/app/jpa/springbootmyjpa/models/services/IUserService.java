package com.matias.springboot.app.jpa.springbootmyjpa.models.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;

public interface IUserService {

    public Optional<User> findOne(Long id);

    public Map<String, Object> findById(Long id);

    public User findByMail(String mail);

    public List<User> findAll();

    public User save(User user);

    public void deleteById(Long id);

}
