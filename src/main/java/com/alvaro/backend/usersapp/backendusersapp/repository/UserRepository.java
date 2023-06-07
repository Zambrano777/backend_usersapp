package com.alvaro.backend.usersapp.backendusersapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alvaro.backend.usersapp.backendusersapp.models.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
    
}
