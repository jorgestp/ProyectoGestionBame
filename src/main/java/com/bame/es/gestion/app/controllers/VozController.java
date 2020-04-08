package com.bame.es.gestion.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bame.es.gestion.app.models.entity.Marcha;
import com.bame.es.gestion.app.models.entity.Voz;
import com.bame.es.gestion.app.models.service.impl.IMarchaService;

@Controller
@RequestMapping(value = "/voz")
public class VozController {
	
	@Autowired
	IMarchaService marchaService;

	@RequestMapping(value = "/form/{id}")
	public String crear(@PathVariable(name = "id") Long id, Map<String, Object> model,RedirectAttributes flash) {
		
		Marcha marcha = marchaService.findById(id);
		
		if(marcha == null) {
			
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/repertorio/lista";
		}
		
		Voz nuevaVoz = new Voz();
		nuevaVoz.setMarcha(marcha);
		model.put("titulo", "Nueva voz para marcha " + marcha.getNombre());
		model.put("voz", nuevaVoz);
		
		return "voz/formVoz";
	}


}
