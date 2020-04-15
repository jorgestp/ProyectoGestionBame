package com.bame.es.gestion.app.controllers;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bame.es.gestion.app.models.entity.Componente;
import com.bame.es.gestion.app.models.entity.Material;
import com.bame.es.gestion.app.models.entity.Prestamo;
import com.bame.es.gestion.app.models.service.impl.IComponenteService;

@Controller
@RequestMapping(value = "/prestamo")
@SessionAttributes("prestamo")
public class PrestamoController {
	
	@Autowired
	private IComponenteService componenteService;
	
	@RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
	public String crearPrestamo(@PathVariable(name = "id") Long id, 
			Map<String, Object> model,
			RedirectAttributes flash) {
		
			Componente componente = componenteService.findById(id);
			
			if(componente == null) {
				flash.addFlashAttribute("error", "El componente no existe");
				return "redirect:/repertorio/lista";
			}
			
			Prestamo prestamo = new Prestamo();
			prestamo.setComponente(componente);
			List<Material> materiales = componenteService.findMateriales();
			
			model.put("titulo", "Prestamo para " + componente.getNombre());
			model.put("prestamo", prestamo);
			model.put("materiales", materiales);
			
			System.out.println(materiales.size());
		
		return "prestamo/formPrestamo";
	}
	
	@GetMapping(value = "/cargar-productos/{id}", produces = {"application/json"})
	public @ResponseBody Material cargarProductos (@PathVariable Long id){
		
		return componenteService.findMaterialById(id);
	}
	
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(Prestamo prestamo,
			BindingResult result,
			RedirectAttributes flash, 
			SessionStatus session) {
			
			componenteService.savePrestamo(prestamo);
			session.setComplete();
		
			flash.addFlashAttribute("success", "Se ha guardado el prestamo correctamente");
			return "redirect:/componentes/ver/" + prestamo.getComponente().getId();
	}

}
