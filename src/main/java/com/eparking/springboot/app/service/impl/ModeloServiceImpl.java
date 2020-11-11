package com.eparking.springboot.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eparking.springboot.app.entity.Modelo;
import com.eparking.springboot.app.repository.IModeloRepository;
import com.eparking.springboot.app.service.IModeloService;

@Service
public class ModeloServiceImpl implements IModeloService {

	@Autowired
	private IModeloRepository moR;
	
	@Override
	@Transactional
	public void insert(Modelo modelo) {

		moR.save(modelo);
	}

	@Override
	public void delete(Integer idModelo) {
		moR.deleteById(idModelo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Modelo> list() {
		
		return moR.findAll();
	}



}
