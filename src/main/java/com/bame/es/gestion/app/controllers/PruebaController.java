package com.bame.es.gestion.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PruebaController {
	
	
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}

}
