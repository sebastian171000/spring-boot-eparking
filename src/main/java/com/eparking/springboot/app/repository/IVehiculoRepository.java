package com.eparking.springboot.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.entity.Vehiculo;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {
	@Query("select v from Vehiculo v where v.codigo =:codigo")
	public Vehiculo findOne(@Param("codigo") Integer codigo);
	
	@Query("select v from Vehiculo v where v.usuario =:usuario")
	public List<Vehiculo> listByUser(@Param("usuario") Usuario usuario);
	
	@Query("select v from Vehiculo v where v.placa like %:busqueda% or v.modelo.marca.nombre like %:busqueda%")
	public List<Vehiculo> listByMarcaPlaca(@Param("busqueda") String busqueda);
	
	@Query("select count(*) from Vehiculo v where v.modelo.codigo =:codigo")
	public int countByModel(@Param("codigo") int codigo);

	
}
