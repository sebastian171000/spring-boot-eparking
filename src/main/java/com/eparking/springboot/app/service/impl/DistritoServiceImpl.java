package com.eparking.springboot.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eparking.springboot.app.entity.Distrito;
import com.eparking.springboot.app.repository.IDistritoRepository;
import com.eparking.springboot.app.service.IDistritoService;

@Service
public class DistritoServiceImpl implements IDistritoService {

	@Autowired
	private IDistritoRepository veR;
	
	@Override
	@Transactional
	public void insert(Distrito distrito) {

		veR.save(distrito);
	}

	@Override
	public void delete(Integer idDistrito) {
		veR.deleteById(idDistrito);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Distrito> list() {
		
		return veR.findAll();
	}



}
