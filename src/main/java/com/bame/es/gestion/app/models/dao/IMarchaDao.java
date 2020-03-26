package com.bame.es.gestion.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bame.es.gestion.app.models.entity.Marcha;

public interface IMarchaDao extends PagingAndSortingRepository<Marcha, Long>{
	
	

}
