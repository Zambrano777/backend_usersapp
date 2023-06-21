package com.alvaro.backend.usersapp.backendusersapp.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alvaro.backend.usersapp.backendusersapp.models.dto.ProductoDto;
import com.alvaro.backend.usersapp.backendusersapp.models.entity.Producto;
import com.alvaro.backend.usersapp.backendusersapp.service.ProductoDataServiceImpl;
import com.alvaro.backend.usersapp.backendusersapp.service.ProductoServiceImpl;


@RestController
public class ProductoController {

	@Autowired
	private ProductoServiceImpl uploadService;

	@Autowired
	private ProductoDataServiceImpl serviceProductoData;

	@PostMapping("/productos/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("archivoDos") MultipartFile archivoDos,
			@RequestParam("archivoTres") MultipartFile archivoTres,
			@RequestParam("archivoCuatro") MultipartFile archivoCuatro,
			@RequestParam("archivoCinco") MultipartFile archivoCinco, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Producto producto = serviceProductoData.productoPorId(id);

		if (!archivo.isEmpty()) {

			String nombreArchivo = null;
			String nombreArchivoDos = null;
			String nombreArchivoTres = null;
			String nombreArchivoCuatro = null;
			String nombreArchivoCinco = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
				nombreArchivoDos = uploadService.copiar(archivoDos);
				nombreArchivoTres = uploadService.copiar(archivoTres);
				nombreArchivoCuatro = uploadService.copiar(archivoCuatro);
				nombreArchivoCinco = uploadService.copiar(archivoCinco);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response,
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = producto.getFoto();
			String nombreFotoAnteriorDos = producto.getFotoDos();
			String nombreFotoAnteriorTres = producto.getFotoTres();
			String nombreFotoAnteriorCuatro = producto.getFotoCuatro();
			String nombreFotoAnteriorCinco = producto.getFotoCinco();

			uploadService.eliminar(nombreFotoAnterior);
			uploadService.eliminar(nombreFotoAnteriorDos);
			uploadService.eliminar(nombreFotoAnteriorTres);
			uploadService.eliminar(nombreFotoAnteriorCuatro);
			uploadService.eliminar(nombreFotoAnteriorCinco);

			producto.setFoto(nombreArchivo);
			producto.setFotoDos(nombreArchivoDos);
			producto.setFotoTres(nombreArchivoTres);
			producto.setFotoCuatro(nombreArchivoCuatro);
			producto.setFotoCinco(nombreArchivoCinco);

			serviceProductoData.registraProducto(producto);

			response.put("producto", producto);
			response.put("mensaje", "Has subido correctamente las imagenes: " +
					nombreArchivo + " " + nombreArchivoDos + " " + nombreArchivoTres + " " +
					nombreArchivoCuatro + " " + nombreArchivoCinco);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/productos")
	public ResponseEntity<?> listaProduto(){
		List<ProductoDto> productos = serviceProductoData.listaProductoExpose();
		return ResponseEntity.ok(productos);
	}

	// @GetMapping("/uploads/img/{nombreFoto:.+}")
	// public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

	// Resource recurso = null;

	// try {
	// recurso = uploadService.cargar(nombreFoto);
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// }

	// HttpHeaders cabecera = new HttpHeaders();
	// cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	// recurso.getFilename() + "\"");

	// return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	// }

}
