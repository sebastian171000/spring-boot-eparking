package com.eparking.springboot.app.controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eparking.springboot.app.entity.Reserva;
import com.eparking.springboot.app.service.IReservaService;
import com.eparking.springboot.app.service.IVehiculoService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
	
	@Autowired
	private IReservaService reService;
	
	@Autowired
	private IVehiculoService veService;
	
	//crear reserva
	@GetMapping("/new")
	public String newReserva(Model model) {
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("boton","Guardar");
		model.addAttribute("titulo","Nuevo Vehículo");
		model.addAttribute("listVehiculos", veService.list());
		return "reserva/reserva";
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
		model.put("reserva", reserva);
		model.put("boton","Actualizar");
		model.put("titulo","Actualizar Vehículo");
		return "reserva/reserva";
	}
	
	
	@PostMapping("/save")
	public String saveReserva(@Valid Reserva reserva, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("listVehiculos", veService.list());
			model.addAttribute("reserva", reserva);
			model.addAttribute("boton","Guardar");
			return "reserva/reserva";
		}else {
			model.addAttribute("mensaje", "Se guardo correctamente el reserva");
			reService.insert(reserva);
		}
		model.addAttribute("listReservas", reService.list());
		return "redirect:/reservas/list";
	}
	
	//listar reservas
	@GetMapping("/list")
	public String listReservas(Model model) {
		try {
			model.addAttribute("reserva", new Reserva());
			model.addAttribute("listReservas", reService.list());
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "reserva/listReservas";
	}
	
	//eliminar
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				reService.delete(id);
				model.put("mensaje", "Se elimino el reserva correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se pudo eliminar la departamento");
		}
		model.put("listReservas", reService.list());
		return "redirect:/reservas/list";
	}
	
}
