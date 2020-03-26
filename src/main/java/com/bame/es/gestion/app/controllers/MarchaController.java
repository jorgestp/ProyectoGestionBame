package com.bame.es.gestion.app.controllers;

//import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bame.es.gestion.app.models.entity.Marcha;
import com.bame.es.gestion.app.models.service.IMarchaService;
import com.bame.es.gestion.app.pageRender.PageRender;



@Controller
@RequestMapping("/repositorio")
public class MarchaController {
	
	
	@Autowired
	private IMarchaService marchaService;
	
	@GetMapping(value = "/")
	public String ListarRepositorio(@RequestParam(name = "page", defaultValue = "0") int page,
			Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Marcha> marchas = marchaService.findAll(pageRequest);
		
		PageRender<Marcha> pageRender = new PageRender<Marcha>("/repositorio/", marchas);
		
		
		model.put("marchas", marchas);
		model.put("page", pageRender);
		
		return "repositorio";
	}

}
