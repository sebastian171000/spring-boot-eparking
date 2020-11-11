package com.eparking.springboot.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eparking.springboot.app.entity.Reserva;
import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.repository.IReservaRepository;
import com.eparking.springboot.app.service.IReservaService;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepository reR;
	
	@Override
	@Transactional
	public void insert(Reserva reserva) {

		reR.save(reserva);
	}

	@Override
	public void delete(Integer idReserva) {
		reR.deleteById(idReserva);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reserva> list() {
		
		return reR.findAll();
	}

	@Override
	public Reserva findOne(Integer idReserva) {
		return reR.findOne(idReserva);
	}

	@Override
	public List<Reserva> listByUserVehiculo(Usuario usuario) {
		return reR.listByUserVehiculo(usuario);
	}

	@Override
	public List<Reserva> listByUserEstacionamiento(Usuario usuario) {
		return reR.lisByUserEstacionamiento(usuario);
	}

	@Override
	public List<Reserva> listByUserEstacionamientoHistorial(Usuario usuario) {
		return reR.listByUserEstacionamientoHistorial(usuario);
	}

	@Override
	public List<Reserva> listByUserVehiculoHistorial(Usuario usuario) {
		return reR.listByUserVehiculoHistorial(usuario);
	}

	@Override
	public List<Reserva> listByFecha(Date fecha1) {
		return reR.listByFecha(fecha1);
	}

}
