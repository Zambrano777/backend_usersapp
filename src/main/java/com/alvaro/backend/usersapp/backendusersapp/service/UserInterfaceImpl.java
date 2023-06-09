package com.alvaro.backend.usersapp.backendusersapp.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvaro.backend.usersapp.backendusersapp.models.entity.Role;
import com.alvaro.backend.usersapp.backendusersapp.models.entity.User;
import com.alvaro.backend.usersapp.backendusersapp.models.request.UserRequest;
import com.alvaro.backend.usersapp.backendusersapp.repository.RoleRepository;
import com.alvaro.backend.usersapp.backendusersapp.repository.UserRepository;

@Service
public class UserInterfaceImpl implements UserInterface {

    @Autowired
    private UserRepository repository;

    @Autowired
    RoleRepository repositoryRole;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> listaUsuarios() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        // Encriptamos password de usuario
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> roleBd = repositoryRole.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        if (roleBd.isPresent()) {
            roles.add(roleBd.orElseThrow());
        }
        user.setRoles(roles);
        return repository.save(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> update(UserRequest user, Long id) {
        Optional<User> userEncontrado = this.findById(id);
        User userOptional = null;
        if (userEncontrado.isPresent()) {
            User userDb = userEncontrado.orElseThrow();
            userDb.setUserName(user.getUserName());
            userDb.setEmail(user.getEmail());
            userOptional = this.save(userDb);
        }
        return Optional.ofNullable(userOptional);
    }

}
