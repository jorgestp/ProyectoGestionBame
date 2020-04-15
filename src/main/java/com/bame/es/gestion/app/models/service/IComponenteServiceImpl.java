package com.bame.es.gestion.app.models.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bame.es.gestion.app.models.dao.IComponenteDao;
import com.bame.es.gestion.app.models.dao.IPrestamoDao;
import com.bame.es.gestion.app.models.entity.Componente;
import com.bame.es.gestion.app.models.entity.Material;
import com.bame.es.gestion.app.models.entity.Prestamo;
import com.bame.es.gestion.app.models.service.impl.IComponenteService;
import com.bame.es.gestion.app.models.service.impl.IMaterialDao;

@Service
public class IComponenteServiceImpl implements IComponenteService {

	@Autowired
	private IComponenteDao componentedao;
	
	@Autowired
	private IPrestamoDao prestamodao;
	@Autowired
	private IMaterialDao materialdao;
	
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
	@Transactional(readOnly = true)
	public Componente findById(Long id) {
		// TODO Auto-generated method stub
		return componentedao.findById(id).orElse(null);
	}



	@Override
	public void delete(Componente componente) {
		
		componentedao.delete(componente);
		
	}



	@Override
	@Transactional
	public Prestamo savePrestamo(Prestamo prestamo) {
		
		return prestamodao.save(prestamo);
	}



	@Override
	@Transactional(readOnly = true)
	public Prestamo findPrestamoById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@Transactional(readOnly = true)
	public Material findMaterialById(Long id) {
		// TODO Auto-generated method stub
		return materialdao.findById(id).orElse(null);
	}

}
