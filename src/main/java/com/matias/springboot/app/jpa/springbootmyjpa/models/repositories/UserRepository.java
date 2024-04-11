package com.matias.springboot.app.jpa.springbootmyjpa.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from User u where u.id = ?1")
    public Optional<User> findOne(Long id);

    @Query("select u from User u where u.mail = ?1")
    public User findByMail(String mail);

}
