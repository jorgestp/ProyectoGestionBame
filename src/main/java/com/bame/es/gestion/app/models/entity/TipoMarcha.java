package com.bame.es.gestion.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_marchas")
public class TipoMarcha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	String nombre;
	String descripcion;
	
	@OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
	private List<Marcha> marchas;
	
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

	public List<Marcha> getMarchas() {
		return marchas;
	}

	public void setMarchas(List<Marcha> marchas) {
		this.marchas = marchas;
	}
	
	
}
