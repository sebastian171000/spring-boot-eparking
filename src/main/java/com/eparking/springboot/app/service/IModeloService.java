package com.eparking.springboot.app.service;

import java.util.List;

import com.eparking.springboot.app.entity.Modelo;

public interface IModeloService {
	public void insert(Modelo modelo);
	public void delete(Integer idModelo);
	List<Modelo> list();
}
