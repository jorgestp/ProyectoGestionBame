package com.bame.es.gestion.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bame.es.gestion.app.models.entity.Prestamo;

public interface IPrestamoDao extends PagingAndSortingRepository<Prestamo, Long>  {
	
	@Query("select f from Prestamo f join fetch f.componente c join fetch f.itemsPrestamo l join fetch l.material where f.id=?1")
	public Prestamo fetchByIdWithClienteWithItemFacturaWithProducto (Long id);

}
