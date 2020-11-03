package com.eparking.springboot.app.entity;

import java.io.Serializable;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="estacionamiento")
public class Estacionamiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Estacionamiento")
	private int codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="tarifa")
	private int tarifa;
	
	@Column(name="espacios")
	private int espacios;
	
	@Column(name="direccion")
	private String direccion;
	
	
	
	@ManyToOne
	@JoinColumn(name="ID_Distrito",nullable=false)
	private Distrito distrito;
	
	@OneToOne
	@JoinColumn(name="ID_Usuario")
	private Usuario usuario ;
	
	

	public Estacionamiento() {
		super();
	}



	public Estacionamiento(int codigo, String nombre, int tarifa, int espacios, Usuario usuario, Distrito distrito) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.tarifa = tarifa;
		this.espacios = espacios;
		this.distrito = distrito;
	}



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getTarifa() {
		return tarifa;
	}



	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}



	public int getEspacios() {
		return espacios;
	}



	public void setEspacios(int espacios) {
		this.espacios = espacios;
	}

	public Distrito getDistrito() {
		return distrito;
	}



	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	
	
	

	

	

	
	
}

