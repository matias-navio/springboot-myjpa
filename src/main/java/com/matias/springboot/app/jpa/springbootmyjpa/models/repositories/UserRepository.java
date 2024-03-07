package com.matias.springboot.app.jpa.springbootmyjpa.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    @Query("select u from User u where u.id = ?1")
    public User findOne(Long id);

}
