package com.alvaro.backend.usersapp.backendusersapp.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ProductoService {
    

    public Resource cargar(String nombreFoto, String archivoDos, String archivoTres, String archivoCuatro, String archivoCinco) throws MalformedURLException;
	public String copiar(MultipartFile archivo) throws IOException;
	public boolean eliminar(String nombreFoto);
	public Path getPath(String nombreFoto);

}
