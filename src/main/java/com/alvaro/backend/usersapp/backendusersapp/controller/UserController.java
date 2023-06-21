package com.alvaro.backend.usersapp.backendusersapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.backend.usersapp.backendusersapp.models.dto.UserDto;
import com.alvaro.backend.usersapp.backendusersapp.models.entity.User;
import com.alvaro.backend.usersapp.backendusersapp.models.request.UserRequest;
import com.alvaro.backend.usersapp.backendusersapp.service.UserInterfaceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserInterfaceImpl service;

    @GetMapping
    public List<UserDto> listaUsuarios() {

        return service.listaUsuarios();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encuentraPorId(@PathVariable Long id) {

        Optional<UserDto> usuarioEncontrado = service.findById(id);

        if (usuarioEncontrado.isPresent()) {
            return ResponseEntity.ok(usuarioEncontrado);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> registraUsuario(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizaUsuario(@Valid @RequestBody UserRequest user, BindingResult result,
            @PathVariable Long id) {

        if (result.hasErrors()) {
            return validation(result);
        }

        Optional<UserDto> o = service.update(user, id);
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {

        Optional<UserDto> usuarioEncontrado = service.findById(id);
        if (usuarioEncontrado.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> errors.put(error.getField(),
                "El campo " + error.getField() + " " + error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
