package com.bame.es.gestion.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bame.es.gestion.app.models.dao.IMarchaDao;
import com.bame.es.gestion.app.models.dao.IVozDao;
import com.bame.es.gestion.app.models.entity.Marcha;
import com.bame.es.gestion.app.models.entity.Voz;
import com.bame.es.gestion.app.models.service.impl.IMarchaService;

@Service
public class MarchaServiceImpl implements IMarchaService {

	@Autowired
	private IMarchaDao marchadao;
	
	@Autowired
	private IVozDao vozdao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Marcha> findAll() {
		
		return (List<Marcha>) marchadao.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Marcha> findAll(Pageable pageable) {
		
		return marchadao.findAll(pageable);
	}

	@Transactional
	@Override
	public Marcha save(Marcha marcha) {
		
		return marchadao.save(marcha);
	}

	@Transactional(readOnly = true)
	@Override
	public Marcha findById(Long id) {
		
		return marchadao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		marchadao.deleteById(id);
		
	}

	@Override
	@Transactional
	public void saveVoz(Voz voz) {
		vozdao.save(voz);
		
	}
	
	

}
