package com.eparking.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eparking.springboot.app.entity.Vehiculo;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {
	@Query("select v from Vehiculo v where v.codigo =:codigo")
	public Vehiculo findOne(@Param("codigo") Integer codigo);
	
	/*@Query("update Vehiculo v set v=:v")
	public void merge(@Param("v") Vehiculo v);*/
}
