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
	
	@NotEmpty(message =  "La marca es obligatoria")
	@Column(name="Marca")
	private String marca;
	
	@NotEmpty(message = "El modelo es obligatorio")
	@Column(name="Modelo")
	private String modelo;
	
	@ManyToOne
	@JoinColumn(name="ID_Usuario")
	private Usuario usuario;
	
	@Column(name="Estado")
	private Boolean estado = false;
	

	public Vehiculo() {
		super();
	}


	public Vehiculo(int codigo, String placa, String marca, String modelo, Usuario usuario, Boolean estado) {
		super();
		this.codigo = codigo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.usuario = usuario;
		this.estado = estado;
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


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Boolean getEstado() {
		return estado;
	}


	public void setEstado(Boolean estado) {
		this.estado = estado;
	}


	


	

	

	

	
	
}

