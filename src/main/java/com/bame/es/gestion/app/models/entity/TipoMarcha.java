package com.bame.es.gestion.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_marcha")
public class TipoMarcha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	String nombre;
	String descripcion;
	
	
	public TipoMarcha() {
		
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}