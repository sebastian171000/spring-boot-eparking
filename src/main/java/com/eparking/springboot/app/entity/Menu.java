package com.eparking.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="url")
	private String url;
	
	@Column(name="tipoUsuario")
	private String tipoUsuario;
	
	@ManyToOne
	@JoinColumn(name="codigo_submenu")
	private Menu submenu;
	
	@Column(name="estado")
	private boolean estado = true;

	

	public Menu() {
		super();
	}



	public Menu(int codigo, String nombre, String tipo, String url, String tipoUsuario, Menu submenu, boolean estado) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.url = url;
		this.tipoUsuario = tipoUsuario;
		this.submenu = submenu;
		this.estado = estado;
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



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getTipoUsuario() {
		return tipoUsuario;
	}



	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}



	public Menu getSubmenu() {
		return submenu;
	}



	public void setSubmenu(Menu submenu) {
		this.submenu = submenu;
	}



	public boolean isEstado() {
		return estado;
	}



	public void setEstado(boolean estado) {
		this.estado = estado;
	}



	

	

	
}
