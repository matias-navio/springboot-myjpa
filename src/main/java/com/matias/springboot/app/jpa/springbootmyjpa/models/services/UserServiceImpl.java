package com.matias.springboot.app.jpa.springbootmyjpa.models.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;
import com.matias.springboot.app.jpa.springbootmyjpa.models.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository repository;

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.ofNullable(repository.findOne(id).orElse(null));
    }

    @Override
    public Map<String, Object> findById(Long id) {
        Optional<User> userOptional = repository.findOne(id);
        Map<String, Object> data = new HashMap<>();

        userOptional.ifPresent(u -> {
            data.put("id", userOptional.get().getId());
            data.put("name", userOptional.get().getName());
            data.put("lastname", userOptional.get().getLastName());
            data.put("mail", userOptional.get().getMail());
        });
        return data;
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

    @Override
    public User findByMail(String mail) {
        return repository.findByMail(mail);
    }
}
