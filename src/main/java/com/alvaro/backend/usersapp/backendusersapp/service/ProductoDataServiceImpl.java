package com.alvaro.backend.usersapp.backendusersapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvaro.backend.usersapp.backendusersapp.models.dto.ProductoDto;
import com.alvaro.backend.usersapp.backendusersapp.models.entity.Producto;

import com.alvaro.backend.usersapp.backendusersapp.repository.ProductoRepository;

@Service
public class ProductoDataServiceImpl implements ProductoDataService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<Producto> listaProducto() {
        return repository.findAll();
    }

    @Override
    public Producto registraProducto(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public void eliminaProducto(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Producto productoPorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<ProductoDto> listaProductoExpose() {
        return repository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ProductoDto convertEntityToDto(Producto producto) {
        ProductoDto productoDTO = new ProductoDto();
        productoDTO.setTitulo(producto.getTitulo());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setFoto(producto.getFoto());
        productoDTO.setPrecio(producto.getPrecio());
        return productoDTO;
    }

}
