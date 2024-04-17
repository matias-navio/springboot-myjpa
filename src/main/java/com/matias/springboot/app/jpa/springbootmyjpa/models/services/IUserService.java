package com.matias.springboot.app.jpa.springbootmyjpa.models.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;

public interface IUserService {

    public Optional<User> findOne(Long id);

    public Map<String, Object> findByIdMap(Long id);

    public List<User> findAll();

    public User create(User user);

    public Optional<User> update(Long id, User user);

    public Optional<User> deleteById(Long id);

}
