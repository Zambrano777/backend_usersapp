package com.alvaro.backend.usersapp.backendusersapp.service;

import java.util.List;

import com.alvaro.backend.usersapp.backendusersapp.models.dto.ProductoDto;
import com.alvaro.backend.usersapp.backendusersapp.models.entity.Producto;


public interface ProductoDataService {

    List<Producto> listaProducto();

    Producto registraProducto(Producto producto);

    void eliminaProducto(Long id);

    Producto productoPorId(Long id);

    List<ProductoDto> listaProductoExpose();

}
