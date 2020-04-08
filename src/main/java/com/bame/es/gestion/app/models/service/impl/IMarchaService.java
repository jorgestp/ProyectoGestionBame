package com.bame.es.gestion.app.models.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bame.es.gestion.app.models.entity.Marcha;
import com.bame.es.gestion.app.models.entity.Voz;


public interface IMarchaService {

	public List<Marcha> findAll();
	
	public Page<Marcha> findAll(Pageable pageable);
	
	public Marcha save(Marcha marcha);
	
	public Marcha findById(Long id);
	
	public void deleteById(Long id);
	
	public void saveVoz(Voz voz);
}
