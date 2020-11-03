package com.eparking.springboot.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.repository.IUsuarioRepository;
import com.eparking.springboot.app.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usR;
	
	@Override
	@Transactional
	public void insert(Usuario usuario) {

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
	public Usuario login(String username, String clave) {
		// TODO Auto-generated method stub
		return usR.Login(username, clave);
	}

}
