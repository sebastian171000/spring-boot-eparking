package com.eparking.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.service.IUsuarioService;

@Controller
@SessionAttributes("usuarioSesion")
public class SessionController {
	@Autowired
	private IUsuarioService usService;
	
	@GetMapping("/login")
	public String index() {
		return "login";
	}
	
	@PostMapping("/login/post")
	public String IniciarSesion(Usuario usuario, Model model) {
		Usuario us = usService.login(usuario.getUsername(), usuario.getClave());
		model.addAttribute("usuarioSesion", us);
		if(us != null) {
			return "redirect:/home";
		}
		model.addAttribute("error","Error al iniciar sesion "+usuario.getUsername() + " " + usuario.getClave());
		return "login";
	}
	
	@GetMapping("/logout")
	public String CerrarSesion(SessionStatus status) {
		status.setComplete();
		return "redirect:/login";
	}
}
