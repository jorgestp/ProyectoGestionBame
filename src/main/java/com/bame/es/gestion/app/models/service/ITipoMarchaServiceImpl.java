package com.bame.es.gestion.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bame.es.gestion.app.models.dao.ITipoMarchaDao;
import com.bame.es.gestion.app.models.entity.TipoMarcha;
import com.bame.es.gestion.app.models.service.impl.ITipoMarchaService;

@Service
public class ITipoMarchaServiceImpl implements ITipoMarchaService {
	
	@Autowired
	private ITipoMarchaDao tipoMarchadao;
	
	
	public TipoMarcha findById(Long id) {
		// TODO Auto-generated method stub
		return tipoMarchadao.findById(id).orElse(null);
	}

}
