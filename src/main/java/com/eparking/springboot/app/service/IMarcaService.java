package com.eparking.springboot.app.service;

import java.util.List;

import com.eparking.springboot.app.entity.Marca;

public interface IMarcaService {
	public void insert(Marca marca);
	public void delete(Integer idMarca);
	List<Marca> list();
}
