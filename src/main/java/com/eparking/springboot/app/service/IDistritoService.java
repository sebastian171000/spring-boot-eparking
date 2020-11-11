package com.eparking.springboot.app.service;

import java.util.List;

import com.eparking.springboot.app.entity.Distrito;

public interface IDistritoService {
	public void insert(Distrito distrito);
	public void delete(Integer idDistrito);
	List<Distrito> list();
}
