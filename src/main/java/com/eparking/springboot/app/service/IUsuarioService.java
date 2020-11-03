package com.eparking.springboot.app.service;

import java.util.List;

import com.eparking.springboot.app.entity.Usuario;

public interface IUsuarioService {
	public void insert(Usuario usuario);
	public void delete(Integer idUsuario);
	List<Usuario> list();
	public Usuario findOne(Integer idUsuario);
	public Usuario login(String username, String clave);
}
