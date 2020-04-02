package com.bame.es.gestion.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

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
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public byte[] mostrar(String filename) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Path getPath (String filename) {
		
		//Devolvemos la ruta absoluta donde se encuentra FOLDER, concatenadondo el nombre del archivo
		return Paths.get(FOLDER).resolve(filename).toAbsolutePath();
	}

}
