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
@Table(name="vehiculo")
public class Vehiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Vehiculo")
	private int codigo;
	
	@NotEmpty(message = "La placa es obligatoria")
	@Column(name="Placa", unique = true)
	private String placa;
	
	@ManyToOne
	@JoinColumn(name="ID_Usuario")
	private Usuario usuario;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_Modelo")
	private Modelo modelo;
	
	

	public Vehiculo() {
		super();
	}



	public Vehiculo(int codigo, @NotEmpty(message = "La placa es obligatoria") String placa, Usuario usuario,
			Modelo modelo) {
		super();
		this.codigo = codigo;
		this.placa = placa;
		this.usuario = usuario;
		this.modelo = modelo;
	}



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getPlaca() {
		return placa;
	}



	public void setPlaca(String placa) {
		this.placa = placa;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Modelo getModelo() {
		return modelo;
	}



	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}



	


	

	


	

	

	

	
	
}

