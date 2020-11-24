package com.eparking.springboot.app.service;

import java.util.Date;
import java.util.List;

import com.eparking.springboot.app.entity.Reserva;
import com.eparking.springboot.app.entity.Usuario;

public interface IReservaService {
	public void insert(Reserva reserva);
	public void delete(Integer idReserva);
	List<Reserva> list();
	public Reserva findOne(Integer idReserva);
	public List<Reserva> listByUserVehiculo(Usuario usuario);
	public List<Reserva> listByUserEstacionamiento(Usuario usuario);
	public List<Reserva> listByUserEstacionamientoHistorial(Usuario usuario);
	public List<Reserva> listByUserVehiculoHistorial(Usuario usuario);
	public List<Reserva> listByFecha(Date fecha1);

}
