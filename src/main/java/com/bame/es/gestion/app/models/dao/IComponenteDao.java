package com.bame.es.gestion.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bame.es.gestion.app.models.entity.Componente;

public interface IComponenteDao extends PagingAndSortingRepository<Componente, Long> {

}
