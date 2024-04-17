package com.matias.springboot.app.jpa.springbootmyjpa.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.matias.springboot.app.jpa.springbootmyjpa.models.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

    public Optional<Role> findByName(String name);
}
