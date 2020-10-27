package com.eparking.springboot.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		/*if(vehiculo.getCodigo() > 0) {
			veR.merge(vehiculo);
		}*/
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

}
