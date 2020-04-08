package com.bame.es.gestion.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/voz")
public class VozController {

	@RequestMapping(value = "/form/{id}")
	public String crear(@PathVariable(name = "id") Long id) {
		
		return "voz/formVoz";
	}


}
