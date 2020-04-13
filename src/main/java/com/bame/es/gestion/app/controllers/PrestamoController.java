package com.bame.es.gestion.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bame.es.gestion.app.models.entity.Componente;
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
			model.put("titulo", "Prestamo para " + componente.getNombre());
			model.put("prestamo", prestamo);
			
		
		return "prestamo/formPrestamo";
	}

}
