package com.alvaro.backend.usersapp.backendusersapp.repository;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alvaro.backend.usersapp.backendusersapp.models.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    Optional<User> findByUsername(String username);

    // @Query("select u from User u where u.userName = ?1")
    // Optional<User> getUserByUserName(String username);

}
