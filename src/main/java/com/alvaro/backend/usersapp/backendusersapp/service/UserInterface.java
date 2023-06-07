package com.alvaro.backend.usersapp.backendusersapp.service;

import java.util.List;
import java.util.Optional;

import com.alvaro.backend.usersapp.backendusersapp.models.entity.User;
import com.alvaro.backend.usersapp.backendusersapp.models.request.UserRequest;

public interface UserInterface {

    public List<User> listaUsuarios();
    Optional<User> findById(Long id);
    User save(User user);
    void remove(Long id);
    Optional<User> update(UserRequest user, Long id);


}
