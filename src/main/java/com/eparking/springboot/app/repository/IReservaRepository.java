package com.eparking.springboot.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eparking.springboot.app.entity.Reserva;
import com.eparking.springboot.app.entity.Usuario;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Integer> {
	@Query("select r from Reserva r where r.codigo =:codigo")
	public Reserva findOne(@Param("codigo") Integer codigo);
	
	@Query("select r from Reserva r where r.vehiculo.usuario =:usuario and r.estado != 'Rechazado'"
			+ " and r.estado != 'Finalizado'")
	public List<Reserva> listByUserVehiculo(@Param("usuario") Usuario usuario);

	@Query("select r from Reserva r where r.estacionamiento.usuario =:usuario and r.estado != 'Rechazado'"
			+ " and r.estado != 'Finalizado'")
	public List<Reserva> lisByUserEstacionamiento(@Param("usuario") Usuario usuario);
	
	@Query("select r from Reserva r where r.estacionamiento.usuario =:usuario and (r.estado = 'Rechazado'"
			+ " or r.estado = 'Finalizado')")
	public List<Reserva> listByUserEstacionamientoHistorial(@Param("usuario") Usuario usuario); 
	
	@Query("select r from Reserva r where r.vehiculo.usuario =:usuario and (r.estado = 'Rechazado'"
			+ " or r.estado = 'Finalizado')")
	public List<Reserva> listByUserVehiculoHistorial(@Param("usuario") Usuario usuario);
	
	/*@Query("select r from Reserva r where r.fechaReserva >= :fecha1 and r.fechaReserva <= :fecha2")
	public List<Reserva> listByFecha(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);*/
	
	@Query("select r from Reserva r where r.fechaReserva =:fecha1")
	public List<Reserva> listByFecha(@Param("fecha1") Date fecha1);
	
	
} 
