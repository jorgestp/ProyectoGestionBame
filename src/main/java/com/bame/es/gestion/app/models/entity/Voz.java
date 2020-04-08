package com.bame.es.gestion.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "voces")
public class Voz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	
	private String partitura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Marcha marcha;


	public Voz() {
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


	public String getPartitura() {
		return partitura;
	}


	public void setPartitura(String partitura) {
		this.partitura = partitura;
	}


	public Marcha getMarcha() {
		return marcha;
	}


	public void setMarcha(Marcha marcha) {
		this.marcha = marcha;
	}


	@Override
	public String toString() {
		return "Voz [id=" + id + ", nombre=" + nombre + ", partitura=" + partitura + ", marcha=" + marcha + "]";
	}
	
	
	
	

}
