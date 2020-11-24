package com.eparking.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "modelo")
public class Modelo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	@Column(name = "ID_Modelo")
	private Integer codigo;

	@Column(name = "nombre")
	private String nombre;
	
	@ManyToOne()
	@JoinColumn(name = "ID_Marca")
	private Marca marca;

	public Modelo() {
		super();
	}

	

	public Modelo(Integer codigo, String nombre, Marca marca) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.marca = marca;
	}



	public Marca getMarca() {
		return marca;
	}



	public void setMarca(Marca marca) {
		this.marca = marca;
	}



	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
