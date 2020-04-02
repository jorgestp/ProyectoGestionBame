package com.bame.es.gestion.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public class IUploadFileService implements com.bame.es.gestion.app.models.service.impl.IUploadFileService {

	private static final String FOLDER="uploads";
	
	@Override
	public Resource load(String filename) throws MalformedURLException {
		
		
		return null;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String filename) {
		// TODO Auto-generated method stub
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

}
