package com.matias.springboot.app.jpa.springbootmyjpa.models.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.Role;
import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;
import com.matias.springboot.app.jpa.springbootmyjpa.models.repositories.RoleRepository;
import com.matias.springboot.app.jpa.springbootmyjpa.models.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.ofNullable(userRepository.findOne(id).orElse(null));
    }

    @Override
    public Map<String, Object> findByIdMap(Long id) {
        Optional<User> userOptional = userRepository.findOne(id);
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
        return (List<User>) userRepository.findAll();
    }

    public User create(User user){

        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }    

    @Override
    public Optional<User> deleteById(Long id) {
        Optional<User> optionalUser = userRepository.findOne(id);

        optionalUser.ifPresent(user -> {
            userRepository.delete(user);
        });
        return optionalUser;
    }

    @Override
    public Optional<User> update(Long id, User user) {

        Optional<User> optionalUser = userRepository.findOne(id);
        optionalUser.ifPresent(userDb -> {
            userDb.setName(user.getName());
            userDb.setLastName(user.getLastName());
            userDb.setMail(user.getMail());

            userRepository.save(userDb);
        });
        return optionalUser;
    }
}
