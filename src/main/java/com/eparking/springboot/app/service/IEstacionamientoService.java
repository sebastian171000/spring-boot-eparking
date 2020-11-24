package com.eparking.springboot.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eparking.springboot.app.entity.Estacionamiento;
import com.eparking.springboot.app.entity.Usuario;

public interface IEstacionamientoService {
	public void insert(Estacionamiento estacionamiento);
	public void delete(Integer idEstacionamiento);
	List<Estacionamiento> list();
	public Estacionamiento findOne(Integer idEstacionamiento);
	public void insertWithImage(Estacionamiento estacionamiento, MultipartFile imageFile) throws Exception;
	public Estacionamiento findByUser(Usuario usuario);
}
