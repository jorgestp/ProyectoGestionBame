package com.bame.es.gestion.app.models.service.impl;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bame.es.gestion.app.models.entity.Componente;

public interface IComponenteService  {
	
	public List<Componente> findAll();
	
	public Page<Componente> findAll(Pageable pageable);
	
	public Componente save(Componente componente);
	
	public Componente findById(Long id);
	
	public void delete(Componente componente);

}