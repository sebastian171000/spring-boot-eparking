package com.eparking.springboot.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reserva")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Reserva")
	private int codigo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	//@FutureOrPresent
	@Column(name="FechaReserva")
	private Date fechaReserva;
	
	//@NotEmpty
	@Column(name="hora")
	private String hora;
	
	@NotEmpty
	@Column(name="EstadoReserva")
	private String estado="Pendiente";
	
	
	@Column(name="NumeroDeHoras")
	private int nhoras;
	

	@ManyToOne
	@JoinColumn(name="ID_Vehiculo")
	private Vehiculo vehiculo;
	
	@ManyToOne
	@JoinColumn(name="ID_Estacionamiento")
	private Estacionamiento estacionamiento;
	
	
	

	public Reserva() {
		super();
	}




	public Reserva(int codigo, Date fechaReserva, String hora, String estado, int nhoras, Vehiculo vehiculo,
			Estacionamiento estacionamiento) {
		super();
		this.codigo = codigo;
		this.fechaReserva = fechaReserva;
		this.hora = hora;
		this.estado = estado;
		this.nhoras = nhoras;
		this.vehiculo = vehiculo;
		this.estacionamiento = estacionamiento;
	}




	public int getCodigo() {
		return codigo;
	}




	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}




	public Date getFechaReserva() {
		return fechaReserva;
	}




	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}




	public String getHora() {
		return hora;
	}




	public void setHora(String hora) {
		this.hora = hora;
	}




	public String getEstado() {
		return estado;
	}




	public void setEstado(String estado) {
		this.estado = estado;
	}




	



	public int getNhoras() {
		return nhoras;
	}




	public void setNhoras(int nhoras) {
		this.nhoras = nhoras;
	}




	public Vehiculo getVehiculo() {
		return vehiculo;
	}




	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}




	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}




	public void setEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamiento = estacionamiento;
	}



	
	
	
	

	

	

	
	
}

