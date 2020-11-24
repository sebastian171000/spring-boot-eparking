package com.eparking.springboot.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eparking.springboot.app.entity.Estacionamiento;
import com.eparking.springboot.app.entity.Usuario;


@Repository
public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Integer> {
	
	@Query("select e from Estacionamiento e where e.codigo =:codigo")
	public Estacionamiento findOne(@Param("codigo") Integer codigo);
	
	@Query("select e from Estacionamiento e where e.usuario =:usuario")
	public Estacionamiento findByUser(@Param("usuario") Usuario usuario);
}
