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
			@RequestParam(name = "espaciosD") int espaciosD,@RequestParam(name="anticipacionDias") int anticipacionDias,
			@RequestParam(name="anticipacionHoras", required = false) int anticipacionHoras,
			@RequestParam(name = "horaApertura") String horaApertura,
			@RequestParam(name = "horaCierre") String horaCierre,
	RedirectAttributes flash) {
		Estacionamiento estacionamiento = new Estacionamiento();
		estacionamiento = esService.findByUser(usuario);
		
		if(espaciosD>0 && espaciosD <= estacionamiento.getEspacios()) {
			estacionamiento.setEspacios_disponibles(espaciosD);
			esService.insert(estacionamiento);
		}else {
			flash.addFlashAttribute("error", "El valor debe ser > 0 y <= "+estacionamiento.getEspacios());
		}
		if(anticipacionDias>=0) {
			estacionamiento.setAnticipacionDias(anticipacionDias);
			esService.insert(estacionamiento);
		}else {
			flash.addFlashAttribute("error2", "El valor debe ser >= 0");
		}
		
		
		if(anticipacionHoras >= 1 && anticipacionHoras <= 12) {
			estacionamiento.setAnticipacionHoras(anticipacionHoras);
			if(anticipacionDias > 0) {
				estacionamiento.setAnticipacionHoras(0);
			}
			esService.insert(estacionamiento);
		}else {
			if(anticipacionDias == 0) {
				flash.addFlashAttribute("error3", "El valor debe ser entre 1 a 12");
			}
			
		}
		int numeroHoraApertura = Integer.parseInt(horaApertura.substring(0,1)); 
		int numeroHoraCierre = Integer.parseInt(horaCierre.substring(0,1));
		if(numeroHoraApertura < numeroHoraCierre) {
			if(!horaApertura.equals("")) {
				estacionamiento.setHoraApertura(horaApertura);
				esService.insert(estacionamiento);
			}else {
				flash.addFlashAttribute("error4", "Error en la hora de apertura");
			}
			if(!horaCierre.equals("")) {
				estacionamiento.setHoraCierre(horaCierre);
				esService.insert(estacionamiento);
			}else {
				flash.addFlashAttribute("error5", "Error en la hora de cierre");
			}
		}else {
			flash.addFlashAttribute("error6", "La hora de cierre no puede ser menor o igual a la hora de apertura");
		}
		
		
		return "redirect:/home";
	}
	
}
