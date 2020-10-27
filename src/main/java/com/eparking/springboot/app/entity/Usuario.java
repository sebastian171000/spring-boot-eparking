package com.eparking.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Usuario")
	private int codigo;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="tipo")
	private String tipo="C";
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="ID_Persona", nullable=false)
	private Persona persona;
	
	

	public Usuario() {
		super();
	}



	public Usuario(int codigo, String usuario, String clave, String tipo, Persona persona) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.clave = clave;
		this.tipo = tipo;
		this.persona = persona;
	}



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getClave() {
		return clave;
	}



	public void setClave(String clave) {
		this.clave = clave;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Persona getPersona() {
		return persona;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	

	
	

	

	

	
	
}

