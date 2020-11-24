package com.eparking.springboot.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.entity.Vehiculo;
import com.eparking.springboot.app.repository.IVehiculoRepository;
import com.eparking.springboot.app.service.IVehiculoService;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository veR;
	
	@Override
	@Transactional
	public void insert(Vehiculo vehiculo) {

		veR.save(vehiculo);
	}

	@Override
	public void delete(Integer idVehiculo) {
		veR.deleteById(idVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> list() {
		
		return veR.findAll();
	}

	@Override
	public Vehiculo findOne(Integer idVehiculo) {
		return veR.findOne(idVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> listByUser(Usuario usuario) {
		return veR.listByUser(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> listByMarcaPlaca(String busqueda) {
		return veR.listByMarcaPlaca(busqueda);
	}

	@Override
	public int countByModelo(int idVehiculo) {
		return veR.countByModel(idVehiculo);
	}


}
