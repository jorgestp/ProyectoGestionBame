package com.bame.es.gestion.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class IUploadFileService implements com.bame.es.gestion.app.models.service.impl.IUploadFileService {

	
	
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
		// TODO Auto-generated method stub

	}

	@Override
	public void init() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] mostrar(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

}
