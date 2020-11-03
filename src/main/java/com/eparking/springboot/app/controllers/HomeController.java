package com.eparking.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eparking.springboot.app.entity.Usuario;



@Controller
@SessionAttributes("usuarioSesion")
public class HomeController {
	@GetMapping("/home")
	public String index(@ModelAttribute("usuarioSesion") Usuario usuario,Model model)
	{
		model.addAttribute("nombres", usuario.getNombres());
		model.addAttribute("apellidos", usuario.getApellidos());
		if(usuario.getTipo().equals("C")) {
			return "cliente/home";
		}else if(usuario.getTipo().equals("A")) {
			return "administrador/home";
		}else {
			return "";
		}
		
		
	}
}
