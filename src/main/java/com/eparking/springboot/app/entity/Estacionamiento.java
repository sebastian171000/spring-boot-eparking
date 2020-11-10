package com.eparking.springboot.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
	
	//@Column(name="nombre")
	//private String nombre;
	
	@Column(name="tarifa")
	private int tarifa;
	
	@Column(name="espacios")
	private int espacios;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="espacios_disponibles")
	private int espacios_disponibles;
	
	@Column(name = "hora_apertura")
	private String horaApertura = "7:00";
	
	@Column(name = "hora_cierre")
	private String horaCierre = "22:00";
	
	@Column(name = "anticipacion_horas")
	private int anticipacionHoras = 1;
	 
	@Column(name = "anticipacion_dias")
	private int anticipacionDias = 0;
	
	@ManyToOne
	@JoinColumn(name="ID_Distrito",nullable=true)
	private Distrito distrito;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="ID_Usuario")
	private Usuario usuario ;
	
	

	public Estacionamiento() {
		super();
	}



	



	public Estacionamiento(int codigo, int tarifa, int espacios, String direccion, int espacios_disponibles,
			String horaApertura, String horaCierre, int anticipacionHoras, int anticipacionDias, Distrito distrito,
			Usuario usuario) {
		super();
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.espacios = espacios;
		this.direccion = direccion;
		this.espacios_disponibles = espacios_disponibles;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.anticipacionHoras = anticipacionHoras;
		this.anticipacionDias = anticipacionDias;
		this.distrito = distrito;
		this.usuario = usuario;
	}







	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
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



	public int getEspacios_disponibles() {
		return espacios_disponibles;
	}



	public void setEspacios_disponibles(int espacios_disponibles) {
		this.espacios_disponibles = espacios_disponibles;
	}



	public String getHoraApertura() {
		return horaApertura;
	}



	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}



	public String getHoraCierre() {
		return horaCierre;
	}



	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}



	public int getAnticipacionHoras() {
		return anticipacionHoras;
	}



	public void setAnticipacionHoras(int anticipacionHoras) {
		this.anticipacionHoras = anticipacionHoras;
	}



	public int getAnticipacionDias() {
		return anticipacionDias;
	}



	public void setAnticipacionDias(int anticipacionDias) {
		this.anticipacionDias = anticipacionDias;
	}


	
	
	
	

	

	

	
	
}

