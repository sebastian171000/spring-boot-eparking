package com.eparking.springboot.app.controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eparking.springboot.app.entity.Reserva;
import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.service.IEstacionamientoService;
import com.eparking.springboot.app.service.IReservaService;
import com.eparking.springboot.app.service.IVehiculoService;

@Controller
@RequestMapping("/reservas")
@SessionAttributes("usuarioSesion")
public class ReservaController {
	
	@Autowired
	private IReservaService reService;
	
	@Autowired
	private IVehiculoService veService;
	
	@Autowired
	private IEstacionamientoService esService;
	
	@GetMapping("/new")
	public String newReserva(@ModelAttribute("usuarioSesion") Usuario usuario,Model model) {
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("boton","Guardar");
		model.addAttribute("titulo","Nuevo Vehículo");
		model.addAttribute("listVehiculos", veService.listByUser(usuario));
		model.addAttribute("listEstacionamientos", esService.list());
		return "cliente/reserva/reserva";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable(value = "id") Integer id,Map<String, Object> model) {
		
		Reserva reserva = null;
		if(id>0) {
			reserva = reService.findOne(id);
		}else {
			return "redirect:/reservas/list";
		}
		model.put("listVehiculos", veService.list());
		model.put("listEstacionamientos", esService.list());
		model.put("reserva", reserva);
		model.put("boton","Actualizar");
		model.put("titulo","Actualizar Vehículo");
		return "cliente/reserva/reserva";
	}
	
	@PostMapping("/save")
	public String saveReserva(@Valid Reserva reserva, BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("listVehiculos", veService.list());
			model.addAttribute("listEstacionamientos", esService.list());
			model.addAttribute("reserva", reserva);
			model.addAttribute("boton","Guardar");
			return "cliente/reserva/reserva";
		}else {
			model.addAttribute("mensaje", "Se guardo correctamente el reserva");
			reService.insert(reserva);
		}
		model.addAttribute("listReservas", reService.list());
		return "redirect:/reservas/list";
	}
	
	@GetMapping("/list")
	public String listReservas(@ModelAttribute("usuarioSesion") Usuario usuario,Model model) {
		try {
			model.addAttribute("reserva", new Reserva());
			if(usuario.getTipo().equals("C")) {
				model.addAttribute("listReservas", reService.listByUserVehiculo(usuario));
			}
			if(usuario.getTipo().equals("A")) {
				model.addAttribute("listReservas", reService.listByUserEstacionamiento(usuario));
			}
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		if(usuario.getTipo().equals("C")) {
			return "cliente/reserva/listReservas";
		}else {
			return "administrador/reserva/listReservas";
		}
	}
	
	@GetMapping("/listHistorial")
	public String listHistorial(@ModelAttribute("usuarioSesion") Usuario usuario, Model model) {
		try {
			model.addAttribute("reserva", new Reserva());
			if(usuario.getTipo().equals("C")) {
				model.addAttribute("listReservas", reService.listByUserVehiculoHistorial(usuario));
			}
			if(usuario.getTipo().equals("A")) {
				model.addAttribute("listReservas", reService.listByUserEstacionamientoHistorial(usuario));
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		if(usuario.getTipo().equals("C")) {
			return "cliente/reserva/listReservas";
		}else {
			return "administrador/reserva/listReservas";
		}
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				reService.delete(id);
				model.put("mensaje", "Se elimino el reserva correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se pudo eliminar la reserva");
		}
		model.put("listReservas", reService.list());
		return "redirect:/reservas/list";
	}
	
	@GetMapping("/aprobar")
	public String aprobar(Model model, @RequestParam(value="id") Integer id) {
		try {
			Reserva reserva = null;
			if(id!=null && id>0) {
				reserva = reService.findOne(id);
				reserva.setEstado("Aprobado");
				reService.insert(reserva);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se pudo aprobar la reserva");
		}
		return "redirect:/reservas/list";
	}
	
	@GetMapping("/rechazar")
	public String rechazar(Model model, @RequestParam(value="id") Integer id) {
		try {
			Reserva reserva = null;
			if(id!=null && id>0) {
				reserva = reService.findOne(id);
				reserva.setEstado("Rechazado");
				reService.insert(reserva);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se pudo aprobar la reserva");
		}
		return "redirect:/reservas/list";
	}
	
	@GetMapping("/finalizar")
	public String finalizar(Model model, @RequestParam(value="id") Integer id) {
		try {
			Reserva reserva = null;
			if(id!=null && id>0) {
				reserva = reService.findOne(id);
				reserva.setEstado("Finalizado");
				reService.insert(reserva);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se pudo aprobar la reserva");
		}
		return "redirect:/reservas/list";
	}
	
}
