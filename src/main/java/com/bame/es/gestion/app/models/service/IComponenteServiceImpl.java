package com.bame.es.gestion.app.models.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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



	@Override
	public Page<Componente> findAll(org.springframework.data.domain.Pageable pageable) {
		// TODO Auto-generated method stub
		return componentedao.findAll(pageable);
	}

}
