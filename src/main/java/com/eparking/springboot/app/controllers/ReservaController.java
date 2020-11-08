package com.eparking.springboot.app.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eparking.springboot.app.entity.Estacionamiento;
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
	
	@GetMapping("/new/{es}")
	public String newReserva(@PathVariable() Integer es,@ModelAttribute("usuarioSesion") Usuario usuario,Model model) {
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("boton","Guardar");
		model.addAttribute("titulo","Nuevo Vehículo");
		model.addAttribute("listVehiculos", veService.listByUser(usuario));
		model.addAttribute("listEstacionamientos", esService.list());
		model.addAttribute("es", es);
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
	
	@RequestMapping("/detalleEstacionamiento/{id}")
	public String detalleEstacionamiento(@PathVariable(value = "id") Integer id, Model model) {
		Estacionamiento estacionamiento = null;
		if(id>0) {
			estacionamiento = esService.findOne(id);
		}else {
			return "redirect:/reservas/new";
		}
		model.addAttribute("estacionamiento", estacionamiento);
		return "cliente/reserva/detalleEstacionamiento";
	}
	
	@PostMapping("/save")
	public String saveReserva(Reserva reserva,Model model) {
		
		model.addAttribute("mensaje", "Se guardo correctamente el reserva");
		reserva.setHora(reserva.getHora().replace(",", ""));
		reService.insert(reserva);
		
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
		
			return "listReservas";

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
				model.addAttribute("eliminar", "si existe");
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		return "listReservas";
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
			int espaciosD;
			if(id!=null && id>0) {
				reserva = reService.findOne(id);
				espaciosD = reserva.getEstacionamiento().getEspacios_disponibles() - 1;
				reserva.getEstacionamiento().setEspacios_disponibles(espaciosD);
				
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
			int espaciosD;
			if(id!=null && id>0) {
				reserva = reService.findOne(id);
				espaciosD = reserva.getEstacionamiento().getEspacios_disponibles() + 1;
				reserva.getEstacionamiento().setEspacios_disponibles(espaciosD);
				reserva.setEstado("Finalizado");
				reService.insert(reserva);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se pudo aprobar la reserva");
		}
		return "redirect:/reservas/list";
	}
	@GetMapping("/verReserva")
	public String verReserva(Model model, @RequestParam(value = "id") Integer id) {
		String pagina = "";
		Reserva reserva = null;
		try {
			if(id!=null && id>0) {
				reserva = reService.findOne(id);
				model.addAttribute("verReserva",reserva);
				//String tarifa = reserva.getNhoras();
				//int tarifaEntero = Integer.parseInt(tarifa);
				//model.addAttribute("tarifaEntero",tarifaEntero);
				
				pagina = "verReserva";
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se pudo ver la reserva");
		}
		return pagina;
	}
	
}

