package com.eparking.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="persona")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Persona")
	private int codigo;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="apellidos")
	private String apellidos;
	
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="dni")
	private String dni;
	
	
	

	public Persona() {
		super();
	}


	public Persona(int codigo, String nombres, String apellidos, String correo, String telefono, String dni) {
		super();
		this.codigo = codigo;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.dni = dni;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	


	

	
	

	

	

	
	
}

