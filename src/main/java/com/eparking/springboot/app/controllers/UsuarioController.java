package com.eparking.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.service.IUsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	private IUsuarioService usService;
	
	@GetMapping("/registro")
	public String registro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	@PostMapping("/registro/save")
	public String save(@Valid Usuario usuario, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "registro";
		}else {
			model.addAttribute("mensaje", "Se guardo correctamente el usuario");
			usService.insert(usuario);
		}
		return "redirect:/login";
	}
}
