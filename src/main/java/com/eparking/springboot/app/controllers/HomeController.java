package com.eparking.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eparking.springboot.app.entity.Estacionamiento;
import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.service.IEstacionamientoService;



@Controller
@SessionAttributes("usuarioSesion")
public class HomeController {
	
	@Autowired 
	private IEstacionamientoService esService;
	
	@GetMapping("/home")
	public String index(@ModelAttribute("usuarioSesion") Usuario usuario,Model model)
	{
		
		model.addAttribute("nombres", usuario.getNombres());
		model.addAttribute("apellidos", usuario.getApellidos());
		model.addAttribute("username", usuario.getUsername());
		if(usuario.getTipo().equals("C")) {
			return "cliente/home";
		}else if(usuario.getTipo().equals("A")) {
			//Para mostrar valor actual de 'espacios_disponibles'
			Estacionamiento estacionamiento = new Estacionamiento();
			estacionamiento = esService.findByUser(usuario);
			model.addAttribute("estacionamientoActual", estacionamiento);
			return "administrador/home";
		}else {
			return "";
		}
		
	}
	
	@PostMapping("/espacios-disponibles/update")
	public String espaciosDisponiblesUpdate(@ModelAttribute("usuarioSesion") Usuario usuario,
			@RequestParam(name = "espaciosD") int espaciosD, RedirectAttributes flash) {
		Estacionamiento estacionamiento = new Estacionamiento();
		estacionamiento = esService.findByUser(usuario);
		if(espaciosD>0 && espaciosD < estacionamiento.getEspacios()) {
			estacionamiento.setEspacios_disponibles(espaciosD);
			esService.insert(estacionamiento);
		}else {
			flash.addFlashAttribute("error", "El valor debe ser mayor a 0 y menor a "+estacionamiento.getEspacios());
		}
		
		return "redirect:/home";
	}
	
}
