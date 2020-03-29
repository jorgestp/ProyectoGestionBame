package com.bame.es.gestion.app.models.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "marchas")
public class Marcha implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String compositor;
	
	
	//@Column(name = "fecha_creacion")
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat( pattern = "dd-MM-yyyy")
	//@NotNull
	private String fecha_creacion;
	
	//@Column(name = "fecha_alta")
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat( pattern = "dd-MM-yyyy")
	//@NotNull
	//private String fecha_alta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	TipoMarcha tipo;
	
	private String guia;
	
	
	public Marcha() {
		
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

	public String getCompositor() {
		return compositor;
	}

	public void setCompositor(String compositor) {
		this.compositor = compositor;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}



	public TipoMarcha getTipo() {
		return tipo;
	}

	public void setTipo(TipoMarcha tipo) {
		this.tipo = tipo;
	}


	
	public String toString() {
		
		return nombre + " " + compositor;
	}

	public String getGuia() {
		return guia;
	}

	public void setGuia(String guia) {
		this.guia = guia;
	}
	
	

}
