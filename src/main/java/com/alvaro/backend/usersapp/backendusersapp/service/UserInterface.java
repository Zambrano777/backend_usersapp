package com.alvaro.backend.usersapp.backendusersapp.service;

import java.util.List;
import java.util.Optional;

import com.alvaro.backend.usersapp.backendusersapp.models.dto.UserDto;
import com.alvaro.backend.usersapp.backendusersapp.models.entity.User;
import com.alvaro.backend.usersapp.backendusersapp.models.request.UserRequest;

public interface UserInterface {

    public List<UserDto> listaUsuarios();
    Optional<UserDto> findById(Long id);
    UserDto save(User user);
    void remove(Long id);
    Optional<UserDto> update(UserRequest user, Long id);


}
