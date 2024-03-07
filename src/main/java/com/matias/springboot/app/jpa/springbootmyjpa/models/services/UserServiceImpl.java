package com.matias.springboot.app.jpa.springbootmyjpa.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;
import com.matias.springboot.app.jpa.springbootmyjpa.models.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository repository;

    @Override
    public User findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
