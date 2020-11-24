package com.eparking.springboot.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eparking.springboot.app.entity.Usuario;

public interface IUsuarioService {
	public void insert(Usuario usuario) throws Exception;
	public void delete(Integer idUsuario);
	List<Usuario> list();
	public Usuario findOne(Integer idUsuario);
	public Usuario login(String username);
	void insert(Usuario usuario, MultipartFile imageFile) throws Exception;
}
