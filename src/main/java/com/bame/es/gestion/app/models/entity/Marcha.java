package com.bame.es.gestion.app.models.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;





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
	
	@NotEmpty(message = "El nombre de la marcha es requerido")
	private String nombre;
	@NotEmpty(message = "El nombre del compositor es requerido")
	private String compositor;
	
	
	//@Column(name = "fecha_creacion")
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat( pattern = "dd-MM-yyyy")
	//@NotNull
	@NotEmpty(message = "El año de composicion")
	private String fecha_creacion;
	
	//@Column(name = "fecha_alta")
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat( pattern = "dd-MM-yyyy")
	//@NotNull
	//private String fecha_alta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	TipoMarcha tipo;
	
	@OneToMany(mappedBy = "marcha", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Voz> voces;
	
	private String guia;
	
	
	public Marcha() {
		
		voces = new ArrayList<Voz>();
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


	
	@Override
	public String toString() {
		return "Marcha [id=" + id + ", nombre=" + nombre + ", compositor=" + compositor + ", fecha_creacion="
				+ fecha_creacion + ", tipo=" + tipo + ", voces=" + voces + ", guia=" + guia + "]";
	}

	public String getGuia() {
		return guia;
	}

	public void setGuia(String guia) {
		this.guia = guia;
	}

	public List<Voz> getVoces() {
		return voces;
	}

	public void setVoces(List<Voz> voces) {
		this.voces = voces;
	}
	
	public void addVoz(Voz voz) {
		
		voces.add(voz);
	}
	
	

}
