package com.eparking.springboot.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eparking.springboot.app.entity.Estacionamiento;
import com.eparking.springboot.app.repository.IEstacionamientoRepository;
import com.eparking.springboot.app.service.IEstacionamientoService;

@Service
public class EstacionamientoServiceImpl implements IEstacionamientoService {

	@Autowired
	private IEstacionamientoRepository estR;
	
	@Override
	@Transactional
	public void insert(Estacionamiento estacionamiento) {

		estR.save(estacionamiento);
	}

	@Override
	public void delete(Integer idEstacionamiento) {
		estR.deleteById(idEstacionamiento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estacionamiento> list() {
		
		return estR.findAll();
	}

	

}
