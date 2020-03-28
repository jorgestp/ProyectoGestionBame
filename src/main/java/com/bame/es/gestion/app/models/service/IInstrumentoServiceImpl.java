package com.bame.es.gestion.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bame.es.gestion.app.models.dao.IInstrumentoDao;
import com.bame.es.gestion.app.models.entity.Instrumento;

@Service
public class IInstrumentoServiceImpl implements IInstrumentoService {

	@Autowired
	public IInstrumentoDao instrumentodao;
	
	@Override
	public Instrumento findById(Long id) {
		
		return instrumentodao.findById(id).orElse(null);
	}

}
