package com.bame.es.gestion.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bame.es.gestion.app.models.entity.Instrumento;

public interface IInstrumentoDao extends PagingAndSortingRepository<Instrumento, Long>{

}
