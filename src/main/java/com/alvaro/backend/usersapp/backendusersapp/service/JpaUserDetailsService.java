package com.alvaro.backend.usersapp.backendusersapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvaro.backend.usersapp.backendusersapp.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.alvaro.backend.usersapp.backendusersapp.models.entity.User> usuario = userRepository
                .findByUsername(username);

        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Username no existe" + username);
        }
        com.alvaro.backend.usersapp.backendusersapp.models.entity.User userT = usuario.orElseThrow();

        List<GrantedAuthority> authorities = userT.getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

        return new User(userT.getUserName(), userT.getPassword(), true, true, true, true, authorities);

    }

}
