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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bame.es.gestion.app.models.entity.Marcha;
import com.bame.es.gestion.app.models.entity.TipoMarcha;
import com.bame.es.gestion.app.models.service.impl.IMarchaService;
import com.bame.es.gestion.app.models.service.impl.ITipoMarchaService;
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
		
		PageRender<Marcha> pageRender = new PageRender<Marcha>("/repertorio/lista", marchas);
		
		
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
			@RequestParam("file") MultipartFile foto,
			RedirectAttributes flash,
			SessionStatus status) {
		
		///////////////////////////////////////////////////////////
		
		//Si la foto no esta vacia, entra
		if(!foto.isEmpty()) {
			
			
			
		}
		
		
		
		
		//////////////////////////////////////////////////////////////////
		
		TipoMarcha tipo = tipomarchaService.findById(marcha.getTipo().getId());
		//Asignar el tipo de marcha a la clase Marcha
		marcha.setTipo(tipo);
		
		Marcha m =  marchaService.save(marcha);
		
		status.setComplete();
		
		String mensaje="";
		if(marcha.getId() != null) {
			
			mensaje = "Se ha actualizado la marcha " + m.getNombre() 
			+ " " +" correctamente";
			
		}else {
			
			mensaje = "Se ha creado la marcha " + m.getNombre() 
			+ " " +" correctamente";
		}
		flash.addFlashAttribute("success",mensaje);
		
		return "redirect:/repertorio/lista";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash)
	{
		
		
		Marcha m  = null;
		if (id > 0) {

			m = marchaService.findById(id);
			/*
			 * Si el id que se le pasa no esta en la BBDD, devolverá un c nulo
			 */
			if (m == null) {

				flash.addFlashAttribute("error", "La marcha no exixte en la BBDD");
				return "redirect:/repertorio/lista";
			}

		} else {
			flash.addFlashAttribute("error", "La marcha no puede editarse");
			return "redirect:/repertorio/lista";
		}

		model.put("marcha", m);
		model.put("titulo", "Editar componente");
		model.put("boton", "Editar");

		return "formMarcha";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, 
			RedirectAttributes flash) {
		
				if(id>0 && id != null) {
					
					Marcha marcha = marchaService.findById(id);
					marchaService.deleteById(id);
					flash.addFlashAttribute("success", "Marcha '" + marcha.getNombre() + "' eliminada con éxito");
					
				}else {
					
					flash.addFlashAttribute("error", "La marcha no se puede eliminar");
				}
		
				return "redirect:/repertorio/lista";
	}
	
	

}
