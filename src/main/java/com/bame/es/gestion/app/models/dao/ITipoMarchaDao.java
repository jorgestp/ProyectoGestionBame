package com.bame.es.gestion.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bame.es.gestion.app.models.entity.TipoMarcha;

public interface ITipoMarchaDao extends PagingAndSortingRepository<TipoMarcha, Long> {
	

}
