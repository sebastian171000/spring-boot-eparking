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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eparking.springboot.app.entity.Usuario;
import com.eparking.springboot.app.entity.Vehiculo;
import com.eparking.springboot.app.service.IMarcaService;
import com.eparking.springboot.app.service.IModeloService;
import com.eparking.springboot.app.service.IVehiculoService;

@Controller
@RequestMapping("/vehiculos")
@SessionAttributes("usuarioSesion")
public class VehiculoController {
	
	@Autowired
	private IVehiculoService veService;
	
	@Autowired
	private IMarcaService maService;
	
	@Autowired
	private IModeloService moService;
	
	@GetMapping("/new")
	public String newVehiculo(@ModelAttribute("usuarioSesion") Usuario usuario,Model model,RedirectAttributes flash) {
		if(usuario.getTipo().equals("A")) {
			flash.addFlashAttribute("permiso", "cliente");
			return "redirect:/home";
		}
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("listMarcas", maService.list());
		model.addAttribute("listModelos", moService.list());
		model.addAttribute("boton","Guardar");
		model.addAttribute("titulo","Nuevo Vehículo");
		

		return "cliente/vehiculo/vehiculo";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable(value = "id") Integer id,@ModelAttribute("usuarioSesion") Usuario usuario,
			Map<String, Object> model, RedirectAttributes flash) {
		if(usuario.getTipo().equals("A")) {
			flash.addFlashAttribute("permiso", "cliente");
			return "redirect:/home";
		}
		Vehiculo vehiculo = null;
		if(id>0) {
			vehiculo = veService.findOne(id);
		}else {
			return "redirect:/vehiculos/list";
		}
		model.put("listMarcas", maService.list());
		model.put("listModelos", moService.list());
		model.put("vehiculo", vehiculo);
		model.put("boton","Actualizar");
		model.put("titulo","Actualizar Vehículo");
		return "cliente/vehiculo/vehiculoUpdate";
	}
	
	@PostMapping("/save")
	public String saveVehiculo(Vehiculo vehiculo, Model model) {

		try {
			
			model.addAttribute("mensaje", "Se guardo correctamente el vehiculo");
			veService.insert(vehiculo);
			
		} catch (Exception e) {
			model.addAttribute("listMarcas", maService.list());
			model.addAttribute("listModelos", moService.list());
			model.addAttribute("titulo","Nuevo Vehículo");
			model.addAttribute("boton","Guardar");
			model.addAttribute("error_placa", "La placa que ah ingresado ya existe");
			return "cliente/vehiculo/vehiculo";
		}
		
		model.addAttribute("listVehiculos", veService.list());
		return "redirect:/vehiculos/list";
	}
	
	@GetMapping("/list")
	public String listVehiculos(@ModelAttribute("usuarioSesion") Usuario usuario,Model model, RedirectAttributes flash) {
		if(usuario.getTipo().equals("A")) {
			flash.addFlashAttribute("permiso", "cliente");
			return "redirect:/home";
		}
		try {
			model.addAttribute("vehiculo", new Vehiculo());
			model.addAttribute("listVehiculos", veService.listByUser(usuario));
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		return "cliente/vehiculo/listVehiculos";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute("usuarioSesion") Usuario usuario,Map<String, Object> model, 
			@RequestParam(value="id") Integer id, RedirectAttributes flash) {
		if(usuario.getTipo().equals("A")) {
			flash.addFlashAttribute("permiso", "cliente");
			return "redirect:/home";
		}
		try {
			if(id!=null && id>0) {
				veService.delete(id);
				model.put("mensaje", "Se elimino el vehiculo correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se pudo eliminar la departamento");
		}
		model.put("listVehiculos", veService.list());
		return "redirect:/vehiculos/list";
	}
	
	@GetMapping("/buscar")
	public String buscar(@ModelAttribute("usuarioSesion") Usuario usuario,Model model, RedirectAttributes flash, 
			@RequestParam(value = "busqueda") String busqueda) {
		if(usuario.getTipo().equals("A")) {
			flash.addFlashAttribute("permiso", "cliente");
			return "redirect:/home";
		}

		try {
			if(!busqueda.equals("") || !busqueda.equals("Buscar+vehiculo")) {
				model.addAttribute("listVehiculosByMarcaModelo", veService.listByMarcaPlaca(busqueda));
			}else {
				return "redirect:/vehiculos/list";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se pudo encontrar coincidencias");
		}
		return "cliente/vehiculo/listVehiculos";
	}
	
}
