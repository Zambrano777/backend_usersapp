package com.alvaro.backend.usersapp.backendusersapp.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoServiceImpl implements ProductoService {

	private final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

	private final static String DIRECTORIO_UPLOAD = "uploads";

	@Override
	public Resource cargar(String nombreFoto, String archivoDos, String archivoTres,
			String archivoCuatro, String archivoCinco) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Path rutaArchivoDos = getPath(archivoDos);
		Path rutaArchivoTres = getPath(archivoTres);
		Path rutaArchivoCuatro = getPath(archivoCuatro);
		Path rutaArchivoCinco = getPath(archivoCinco);

		log.info(rutaArchivo.toString());
		log.info(rutaArchivoDos.toString());
		log.info(rutaArchivoTres.toString());
		log.info(rutaArchivoCuatro.toString());
		log.info(rutaArchivoCinco.toString());

		Resource recurso = new UrlResource(rutaArchivo.toUri());
		Resource recursoDos = new UrlResource(rutaArchivoDos.toUri());
		Resource recursoTres = new UrlResource(rutaArchivoTres.toUri());
		Resource recursoCuatro = new UrlResource(rutaArchivoCuatro.toUri());
		Resource recursoCinco = new UrlResource(rutaArchivoCinco.toUri());

		if ((!recurso.exists() && !recurso.isReadable()) ||
				(!recursoDos.exists() && !recursoDos.isReadable()) ||
				(!recursoTres.exists() && !recursoTres.isReadable()) ||
				(!recursoCuatro.exists() && !recursoCuatro.isReadable()) ||
				(!recursoCinco.exists() && !recursoCinco.isReadable())) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			rutaArchivoDos = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			rutaArchivoTres = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			rutaArchivoCuatro = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			rutaArchivoCinco = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();

			recurso = new UrlResource(rutaArchivo.toUri());
			recursoDos = new UrlResource(rutaArchivoDos.toUri());
			recursoTres = new UrlResource(rutaArchivoTres.toUri());
			recursoCuatro = new UrlResource(rutaArchivoCuatro.toUri());
			recursoCinco = new UrlResource(rutaArchivoCinco.toUri());

			log.error("Error no se pudo cargar la imagen: " + nombreFoto);

		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");

		Path rutaArchivo = getPath(nombreArchivo);
		log.info(rutaArchivo.toString());

		Files.copy(archivo.getInputStream(), rutaArchivo);

		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		if (nombreFoto != null && nombreFoto.length() > 0) {
			Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}

		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
