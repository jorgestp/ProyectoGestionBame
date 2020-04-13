package com.bame.es.gestion.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "prestamos")
public class Prestamo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//private Componente componente;
	
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat( pattern = "dd-MM-yyyy")
	@NotNull
	private Date createAt;
	private String descripcion;
	private String observacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Componente componente;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "prestamo_id")
	private List<ItemPrestamo> itemsPrestamo;
	
	public Prestamo() {
		
		itemsPrestamo = new ArrayList<ItemPrestamo>();
	}
	
	@PrePersist
	public void prepersist() {
		createAt =  new Date();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Componente getComponente() {
		return componente;
	}


	public void setComponente(Componente componente) {
		this.componente = componente;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public List<ItemPrestamo> getItemsPrestamo() {
		return itemsPrestamo;
	}


	public void setItemsPrestamo(List<ItemPrestamo> itemsPrestamo) {
		this.itemsPrestamo = itemsPrestamo;
	}
	
	

}
