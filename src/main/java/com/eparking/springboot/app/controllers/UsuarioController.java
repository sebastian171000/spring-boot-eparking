package com.eparking.springboot.app.controllers;


import javax.validation.Valid;

import org.jasypt.util.password.rfc2307.RFC2307MD5PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eparking.springboot.app.entity.Estacionamiento;
import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.service.IDistritoService;
import com.eparking.springboot.app.service.IEstacionamientoService;
import com.eparking.springboot.app.service.IUsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	private IUsuarioService usService;
	
	@Autowired 
	private IEstacionamientoService esService;
	
	@Autowired
	private IDistritoService diService;
	
	@GetMapping("/registroCliente")
	public String registroCliente(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "cliente/registro";
	}
	
	@GetMapping("/registroEstacionamiento")
	public String registroEstacionamiento(Model model) {
		model.addAttribute("estacionamiento", new Estacionamiento());
		model.addAttribute("listDistritos", diService.list());
		return "administrador/registro";
	}
	@PostMapping("/registro/save")
	public String save(Usuario usuario, Model model,@RequestParam("imageFile") MultipartFile imageFile) throws Exception {
		model.addAttribute("mensaje", "Se guardo correctamente el usuario");
		usuario.setImagen(imageFile.getOriginalFilename());
		RFC2307MD5PasswordEncryptor encrytor = new RFC2307MD5PasswordEncryptor();
		String encryptedPassword = encrytor.encryptPassword(usuario.getClave());
		usuario.setClave(encryptedPassword);
		usService.insert(usuario,imageFile);
		return "redirect:/login"; 
	}
	
	@PostMapping("/registroEstacionamiento/save") 
	public String saveEstacionamiento(@Valid Estacionamiento estacionamiento, BindingResult result, Model model,@RequestParam(name = "imageFile",required = false) MultipartFile imageFile) throws Exception {
		if(result.hasErrors()) {
			return "administrador/registro";
		}else {
			model.addAttribute("mensaje", "Se guardo correctamente el usuario y estacionamiento");
			estacionamiento.getUsuario().setTipo("A");
			estacionamiento.setEspacios_disponibles(estacionamiento.getEspacios());
			RFC2307MD5PasswordEncryptor encrytor = new RFC2307MD5PasswordEncryptor();
			String encryptedPassword = encrytor.encryptPassword(estacionamiento.getUsuario().getClave());
			estacionamiento.getUsuario().setClave(encryptedPassword);
			if(!imageFile.getOriginalFilename().equals("")) {
				estacionamiento.getUsuario().setImagen(imageFile.getOriginalFilename());
				esService.insertWithImage(estacionamiento, imageFile);
			}else {
				esService.insert(estacionamiento);
			}
			
		}
		return "redirect:/login";
	}
}