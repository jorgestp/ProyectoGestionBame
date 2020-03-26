package com.bame.es.gestion.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bame.es.gestion.app.models.service.IComponenteService;

@Controller
public class PruebaController {
	
	@Autowired
	private IComponenteService componenteService;
	
	@GetMapping(value = "/")
	public String index(Model model) {
		
		model.addAttribute("componentes", componenteService.findAll());
		
		return "index";
	}

}
