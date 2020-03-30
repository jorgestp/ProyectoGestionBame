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



	@Override
	public Componente save(Componente componente) {
		// TODO Auto-generated method stub
		return componentedao.save(componente);
	}



	@Override
	public Componente findById(Long id) {
		// TODO Auto-generated method stub
		return componentedao.findById(id).orElse(null);
	}



	@Override
	public void delete(Componente componente) {
		
		componentedao.delete(componente);
		
	}

}
