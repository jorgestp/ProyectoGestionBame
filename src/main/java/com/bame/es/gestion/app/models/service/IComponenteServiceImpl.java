package com.bame.es.gestion.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bame.es.gestion.app.models.dao.IComponenteDao;
import com.bame.es.gestion.app.models.entity.Componente;

@Service
public class IComponenteServiceImpl implements IComponenteService {

	@Autowired
	private IComponenteDao componentedao;
	
	@Override
	public List<Componente> findAll() {
		
		return (List<Componente>) componentedao.findAll();
	}

}
