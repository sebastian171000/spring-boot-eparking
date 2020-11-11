package com.eparking.springboot.app.service;

import java.util.List;

import com.eparking.springboot.app.entity.Estacionamiento;

public interface IEstacionamientoService {
	public void insert(Estacionamiento estacionamiento);
	public void delete(Integer idEstacionamiento);
	List<Estacionamiento> list();

}
