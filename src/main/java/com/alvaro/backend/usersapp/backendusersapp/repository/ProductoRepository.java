package com.alvaro.backend.usersapp.backendusersapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.alvaro.backend.usersapp.backendusersapp.models.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
