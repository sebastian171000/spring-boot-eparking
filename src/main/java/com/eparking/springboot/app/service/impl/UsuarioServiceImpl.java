package com.eparking.springboot.app.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.repository.IUsuarioRepository;
import com.eparking.springboot.app.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usR;
	
	@Override
	@Transactional
	public void insert(Usuario usuario, MultipartFile imageFile) throws Exception {
		if(!imageFile.getOriginalFilename().equals("")) {
			Path currentPath = Paths.get(".");
			Path absolutePaht = currentPath.toAbsolutePath();
			byte[] bytes = imageFile.getBytes();
			Path path = Paths.get(absolutePaht + "/src/main/resources/static/multipartFile/" + imageFile.getOriginalFilename());
			Files.write(path, bytes);
		}
		
		usR.save(usuario);
	}

	@Override
	public void delete(Integer idUsuario) {
		usR.deleteById(idUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> list() {
		
		return usR.findAll();
	}

	@Override
	public Usuario findOne(Integer idUsuario) {
		return usR.findOne(idUsuario);
	}

	@Override
	public Usuario login(String username) {
		// TODO Auto-generated method stub
		return usR.Login(username);
	}

	@Override
	public void insert(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		
	}

}