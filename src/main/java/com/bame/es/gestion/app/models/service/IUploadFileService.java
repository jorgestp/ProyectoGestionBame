package com.bame.es.gestion.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class IUploadFileService implements com.bame.es.gestion.app.models.service.impl.IUploadFileService {

	private static final String FOLDER="uploads";
	
	@Override
	public Resource load(String filename) throws MalformedURLException {
		
		Path pathFoto = getPath(filename);

		Resource recurso = null;
		
		recurso = new UrlResource(pathFoto.toUri());

		if (!recurso.exists() || !recurso.isReadable()) {

			throw new RuntimeException("Error, no se puede cargar la imagen" + pathFoto.toString());
		}

		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		
		/*
		 * COn esto estamos dandole un identificador unico a cada imagen de modo que si
		 * hay dos imagenes que se llamen igual, no se sobreescriban
		 */
		String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		Path rootPath = getPath(uniqueFileName);
		System.out.println(rootPath.toString());
		
		Files.copy(file.getInputStream(), rootPath);

		return uniqueFileName;
	}

	@Override
	public boolean delete(String filename) {
		
		//obtenemos la ruta absoluta
		Path path = getPath(filename);
		
		
		
		//Obtenemos el file
		File archivo = path.toFile();
		
		//eliminamos el archivo. Preguntamos primero si el archivo existe y se puede leer
		if(archivo.exists() && archivo.canRead()) {
			
			//SI se elimina, mandamo un flash
			if(archivo.delete()) {
				
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteAll() {
		
		FileSystemUtils.deleteRecursively(Paths.get(FOLDER).toFile());

	}

	@Override
	public void init() throws IOException {
		
		Files.createDirectory(Paths.get(FOLDER));

	}


	public Path getPath (String filename) {
		
		//Devolvemos la ruta absoluta donde se encuentra FOLDER, concatenadondo el nombre del archivo
		return Paths.get(FOLDER).resolve(filename).toAbsolutePath();
	}
	
	public byte[] mostrar(String filename) {
		
		byte[] b = null;
		
		Path pathFoto = getPath(filename);
		System.out.println(pathFoto);
		try {
			b = Files.readAllBytes(pathFoto);
		} catch (IOException e) {
			
			return null;
		}
		
		return b;
	}

}
