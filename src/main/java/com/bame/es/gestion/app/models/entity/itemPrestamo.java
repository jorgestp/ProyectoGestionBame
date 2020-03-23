package com.bame.es.gestion.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "items_prestamos")
public class itemPrestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private Material material;
	
	public itemPrestamo() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}*/

	
}
