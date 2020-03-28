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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "componentes")
public class Componente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private String direccion;
	
	private String email;

	private String sexo;
	
	private String dni;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "id_instr")
	/*
	 * Agregamos la siguiente anotacion para ignorar
	 * las propiedades del objeto producto.
	 * Va acompañado de los atributos que queremos ignorar
	 * 
	 * Puede ir tanto aqui como en la clase Producto directamente.
	 */
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Instrumento instrumento;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat( pattern = "dd-MM-yyyy")
	@NotNull
	private Date createAt;
	
	@OneToMany(mappedBy = "componente",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Prestamo> prestamos;
	

	public Componente() {
		
		prestamos = new ArrayList<Prestamo>();

	}
	
	
	

	public String getSexo() {
		return sexo;
	}




	public void setSexo(String sexo) {
		this.sexo = sexo;
	}




	public Instrumento getInstrumento() {
		return instrumento;
	}




	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}




	public List<Prestamo> getPrestamos() {
		return prestamos;
	}




	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}




	@Override
	public String toString() {
		return "Componente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", email=" + email + ", sexo=" + sexo + ", dni=" + dni + ", instrumento=" + instrumento.toString()
				+ ", createAt=" + createAt + ", prestamos=" + prestamos + "]";
	}
	
	
	

}
