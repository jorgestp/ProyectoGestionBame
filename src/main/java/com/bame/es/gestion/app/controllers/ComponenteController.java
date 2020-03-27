package com.bame.es.gestion.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bame.es.gestion.app.models.entity.Componente;
import com.bame.es.gestion.app.models.service.IComponenteService;

@Controller
@RequestMapping(value = "/componentes")
public class ComponenteController {
	
	@Autowired
	private IComponenteService componenteService;
	
	@GetMapping(value = "/lista")
	public String index(Model model) {
		
		List<Componente> componentes = componenteService.findAll();
		
		model.addAttribute("componentes", componentes);
		
		return "index";
	}

}
