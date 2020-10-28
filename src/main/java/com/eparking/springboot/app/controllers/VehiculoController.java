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

import com.eparking.springboot.app.entity.Vehiculo;
import com.eparking.springboot.app.service.IVehiculoService;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	private IVehiculoService veService;
	
	@GetMapping("/new")
	public String newVehiculo(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("boton","Guardar");
		model.addAttribute("titulo","Nuevo Vehículo");
		return "vehiculo/vehiculo";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable(value = "id") Integer id,Map<String, Object> model) {
		
		Vehiculo vehiculo = null;
		if(id>0) {
			vehiculo = veService.findOne(id);
		}else {
			return "redirect:/vehiculos/list";
		}
		model.put("vehiculo", vehiculo);
		model.put("boton","Actualizar");
		model.put("titulo","Actualizar Vehículo");
		return "vehiculo/vehiculo";
	}
	
	@PostMapping("/save")
	public String saveVehiculo(@Valid Vehiculo vehiculo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("boton","Guardar");
			return "vehiculo/vehiculo";
		}else {
			model.addAttribute("mensaje", "Se guardo correctamente el vehiculo");
			veService.insert(vehiculo);
		}
		model.addAttribute("listVehiculos", veService.list());
		return "redirect:/vehiculos/list";
	}
	
	@GetMapping("/list")
	public String listVehiculos(Model model) {
		try {
			model.addAttribute("vehiculo", new Vehiculo());
			model.addAttribute("listVehiculos", veService.list());
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "vehiculo/listVehiculos";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
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
	
}
