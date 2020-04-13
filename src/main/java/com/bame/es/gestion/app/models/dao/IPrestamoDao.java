package com.bame.es.gestion.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bame.es.gestion.app.models.entity.Prestamo;

public interface IPrestamoDao extends PagingAndSortingRepository<Prestamo, Long>  {

}
