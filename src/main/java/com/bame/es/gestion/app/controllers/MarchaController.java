package com.bame.es.gestion.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bame.es.gestion.app.models.entity.Marcha;
import com.bame.es.gestion.app.models.service.IMarchaService;

@Controller
@RequestMapping("/repositorio")
public class MarchaController {
	
	
	@Autowired
	private IMarchaService marchaService;
	
	@GetMapping(value = "/")
	public String ListarRepositorio(Map<String, Object> model) {
		
		List<Marcha> marchas = marchaService.findAll();
		model.put("marchas", marchas);
		
		return "repositorio";
	}

}
