package com.alvaro.backend.usersapp.backendusersapp.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    private String foto;

    private String fotoDos;

    private String fotoTres;

    private String fotoCuatro;

    private String fotoCinco;

    private String precio;

    public Producto(String titulo, String descripcion, String foto, String precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.precio = precio;
    }

    public Producto(){}

    public String getFotoDos() {
        return fotoDos;
    }

    public void setFotoDos(String fotoDos) {
        this.fotoDos = fotoDos;
    }

    public String getFotoTres() {
        return fotoTres;
    }

    public void setFotoTres(String fotoTres) {
        this.fotoTres = fotoTres;
    }

    public String getFotoCuatro() {
        return fotoCuatro;
    }

    public void setFotoCuatro(String fotoCuatro) {
        this.fotoCuatro = fotoCuatro;
    }

    public String getFotoCinco() {
        return fotoCinco;
    }

    public void setFotoCinco(String fotoCinco) {
        this.fotoCinco = fotoCinco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
