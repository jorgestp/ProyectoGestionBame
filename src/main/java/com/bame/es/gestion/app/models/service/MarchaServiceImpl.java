package com.bame.es.gestion.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bame.es.gestion.app.models.dao.IMarchaDao;
import com.bame.es.gestion.app.models.entity.Marcha;

@Service
public class MarchaServiceImpl implements IMarchaService {

	@Autowired
	private IMarchaDao marchadao;
	
	@Override
	public List<Marcha> findAll() {
		
		return (List<Marcha>) marchadao.findAll();
	}

	@Override
	public Page<Marcha> findAll(Pageable pageable) {
		
		return marchadao.findAll(pageable);
	}

}