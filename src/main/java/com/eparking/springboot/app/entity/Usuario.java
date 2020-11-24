package com.eparking.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Usuario")
	private int codigo;
	
	@Pattern(regexp = "^[a-zA-Z0-9\\_\\-]{4,16}$", message = "Su username debe tener como mínimo 4 caracteres y no se aceptan caracter especiales")
	@Column(name="username")
	private String username;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="tipo")
	private String tipo="C";
	
	@Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]{1,40}$", message = "No se aceptan caracteres especiales ni números")
	@Column(name="nombres")
	private String nombres;
	
	@Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]{1,40}$", message = "No se aceptan caracteres especiales ni números")
	@Column(name="apellidos")
	private String apellidos;
	
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "El formato de su correo es incorrecto")
	@Column(name="correo")
	private String correo;
	
	@Pattern(regexp = "^\\d{7,14}$", message = "No se aceptan letras, solo números")
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="imagen")
	private String imagen;
	
	

	public Usuario() {
		super();
	}



	public Usuario(int codigo, String username,String clave,String tipo, String nombres,String apellidos,
			String correo, String telefono,String imagen) {
		super();
		this.codigo = codigo;
		this.username = username;
		this.clave = clave;
		this.tipo = tipo;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.imagen = imagen;
	}



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
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



	public String getImagen() {
		return imagen;
	}



	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	



	

	
	

	

	

	
	
}