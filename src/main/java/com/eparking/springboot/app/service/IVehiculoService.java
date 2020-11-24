package com.eparking.springboot.app.service;

import java.util.List;

import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.entity.Vehiculo;

public interface IVehiculoService {
	public void insert(Vehiculo vehiculo);
	public void delete(Integer idVehiculo);
	List<Vehiculo> list();
	public Vehiculo findOne(Integer idVehiculo);
	List<Vehiculo> listByUser(Usuario usuario);
	List<Vehiculo> listByMarcaPlaca(String busqueda);
	int countByModelo(int idVehiculo); 
}
