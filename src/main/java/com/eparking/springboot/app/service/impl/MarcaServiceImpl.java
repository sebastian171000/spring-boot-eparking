package com.eparking.springboot.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eparking.springboot.app.entity.Marca;
import com.eparking.springboot.app.repository.IMarcaRepository;
import com.eparking.springboot.app.service.IMarcaService;

@Service
public class MarcaServiceImpl implements IMarcaService {

	@Autowired
	private IMarcaRepository maR;
	
	@Override
	@Transactional
	public void insert(Marca marca) {

		maR.save(marca);
	}

	@Override
	public void delete(Integer idMarca) {
		maR.deleteById(idMarca);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Marca> list() {
		
		return maR.findAll();
	}



}
