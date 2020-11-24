package com.eparking.springboot.app.controllers;

import org.jasypt.util.password.rfc2307.RFC2307MD5PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eparking.springboot.app.entity.Estacionamiento;
import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.service.IEstacionamientoService;
import com.eparking.springboot.app.service.IUsuarioService;

@Controller
@SessionAttributes("usuarioSesion")
public class SessionController {
	@Autowired
	private IUsuarioService usService;
	
	@Autowired
	private IEstacionamientoService esService;
	
	@GetMapping("/login")
	public String index(SessionStatus status) {
		status.setComplete();
		return "login";
	}
	
	@PostMapping("/login/post")
	public String IniciarSesion(Usuario usuario, Model model, SessionStatus status) {
		
		
		try {
			RFC2307MD5PasswordEncryptor encrytor = new RFC2307MD5PasswordEncryptor();
			Usuario us = usService.login(usuario.getUsername());
			boolean check = encrytor.checkPassword(usuario.getClave(), us.getClave());
			model.addAttribute("usuarioSesion", us);
			if(us != null && check == true) {
				return "redirect:/home";
			}
			
		} catch (Exception e) {
			status.setComplete();
			model.addAttribute("error","Error al iniciar sesion "+usuario.getUsername() + " " + usuario.getClave());
		}
		return "login";
	}
	
	@GetMapping("/logout")
	public String CerrarSesion() {
		return "redirect:/login";
	}
	
	@RequestMapping("/cuenta")
	public String cuenta() {
		return "cuenta";
	}
	@RequestMapping("/estacionamiento")
	public String estacionamiento(@ModelAttribute(name = "usuarioSesion") Usuario usuario, Model model, RedirectAttributes flash) {
		if(usuario.getTipo().equals("C")) {
			flash.addFlashAttribute("permiso", "administrador");
			return "redirect:/home";
		}
		String pagina = "";
		if(usuario.getTipo().equals("A")) {
			Estacionamiento estacionamiento =  new Estacionamiento();
			estacionamiento = esService.findByUser(usuario);
			model.addAttribute("estacionamiento", estacionamiento);
			pagina = "administrador/estacionamiento";
		}
		return pagina;
	}
}