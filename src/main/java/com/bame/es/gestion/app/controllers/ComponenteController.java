package com.bame.es.gestion.app.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bame.es.gestion.app.models.entity.Componente;

import com.bame.es.gestion.app.models.service.IComponenteService;
import com.bame.es.gestion.app.pageRender.PageRender;



@Controller
@RequestMapping(value = "/componentes")
@SessionAttributes("componente")
public class ComponenteController {
	
	@Autowired
	private IComponenteService componenteService;
	

	
	@GetMapping(value = {"/lista","/"})
	public String index(@RequestParam(name = "page", defaultValue = "0") int page,
			Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Componente> componentes = componenteService.findAll(pageRequest);
		
		PageRender<Componente> pageRender = new PageRender<Componente>("/componentes/lista", componentes);
				
		model.put("componentes", componentes);
		model.put("page", pageRender);

		return "index";
	}
	
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Componente componente = new Componente();
		model.put("componente", componente);
		model.put("titulo", "Nuevo Componente para BAME");

		return "formComponente";
	}

}
