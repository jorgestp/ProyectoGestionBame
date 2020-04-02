package com.bame.es.gestion.app.controllers;

//import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.bame.es.gestion.app.models.entity.Marcha;
import com.bame.es.gestion.app.models.entity.TipoMarcha;
import com.bame.es.gestion.app.models.service.IMarchaService;
import com.bame.es.gestion.app.models.service.ITipoMarchaService;
import com.bame.es.gestion.app.pageRender.PageRender;



@Controller
@RequestMapping(value = "/repertorio")
@SessionAttributes("marcha")
public class MarchaController {
	
	
	@Autowired
	private IMarchaService marchaService;
	
	@Autowired
	private ITipoMarchaService tipomarchaService;
	
	@GetMapping(value = "/lista")
	public String ListarRepositorio(@RequestParam(name = "page", defaultValue = "0") int page,
			Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Marcha> marchas = marchaService.findAll(pageRequest);
		
		PageRender<Marcha> pageRender = new PageRender<Marcha>("/repositorio/lista", marchas);
		
		
		model.put("marchas", marchas);
		model.put("page", pageRender);
		
		return "repositorio";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Marcha marcha = new Marcha();
		model.put("marcha", marcha);
		model.put("titulo", "Nueva Marcha al repertorio");
		
		return "formMarcha";
	}
	
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(Marcha marcha, 
			BindingResult result, 
			Model model,
			RedirectAttributes flash,
			SessionStatus status) {
		
		TipoMarcha tipo = tipomarchaService.findById(marcha.getTipo().getId());
		//Asignar el tipo de marcha a la clase Marcha
		marcha.setTipo(tipo);
		
		 marchaService.save(marcha);
		
		status.setComplete();
		
		return "formMarcha";
	}
	
	
	

}
