package com.matias.springboot.app.jpa.springbootmyjpa.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

    @Query("select u from User u where u.id = ?1")
    public User findOne(Long id);

}
