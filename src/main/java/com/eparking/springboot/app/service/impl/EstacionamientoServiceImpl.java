package com.eparking.springboot.app.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eparking.springboot.app.entity.Estacionamiento;
import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.repository.IEstacionamientoRepository;
import com.eparking.springboot.app.service.IEstacionamientoService;

@Service
public class EstacionamientoServiceImpl implements IEstacionamientoService {

	@Autowired
	private IEstacionamientoRepository estR;
	
	@Override
	@Transactional
	public void insertWithImage(Estacionamiento estacionamiento, MultipartFile imageFile) throws Exception {
		Path currentPath = Paths.get(".");
		Path absolutePaht = currentPath.toAbsolutePath();
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(absolutePaht + "/src/main/resources/static/multipartFile/" + imageFile.getOriginalFilename());
		Files.write(path, bytes);
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

	@Override
	public Estacionamiento findOne(Integer idEstacionamiento) {
		return estR.findOne(idEstacionamiento);
	}

	@Override
	public Estacionamiento findByUser(Usuario usuario) {
		return estR.findByUser(usuario);
	}

	@Override
	public void insert(Estacionamiento estacionamiento) {
		estR.save(estacionamiento);
	}

	

}
