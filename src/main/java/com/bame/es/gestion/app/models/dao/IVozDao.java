package com.bame.es.gestion.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bame.es.gestion.app.models.entity.Voz;

public interface IVozDao extends PagingAndSortingRepository<Voz, Long> {

}
