package com.bame.es.gestion.app.models.service.impl;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bame.es.gestion.app.models.entity.Componente;
import com.bame.es.gestion.app.models.entity.Material;
import com.bame.es.gestion.app.models.entity.Prestamo;

public interface IComponenteService  {
	
	public List<Componente> findAll();
	
	public Page<Componente> findAll(Pageable pageable);
	
	public Componente save(Componente componente);
	
	public Componente findById(Long id);
	
	public void delete(Componente componente);
	
	public Prestamo savePrestamo(Prestamo prestamo);
	
	public Prestamo findPrestamoById(Long id);
	
	public Material findMaterialById(Long id);
	
	public List<Material> findMateriales();

}
