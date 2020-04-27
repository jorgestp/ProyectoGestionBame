package com.bame.es.gestion.app.controllers;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bame.es.gestion.app.models.entity.Componente;
import com.bame.es.gestion.app.models.service.impl.IComponenteService;
import com.bame.es.gestion.app.models.service.impl.IInstrumentoService;
import com.bame.es.gestion.app.pageRender.PageRender;
import com.bame.es.gestion.app.pdf.ReportesService;





@Controller

@SessionAttributes("componente")
public class ComponenteController {
	
	@Autowired
	private IComponenteService componenteService;
	@Autowired
	private IInstrumentoService instrumentoService;
	
	@Autowired
	private ReportesService reporteService;
	

	
	@GetMapping(value = {"/lista","/"})
	public String index(@RequestParam(name = "page", defaultValue = "0") int page,
			Map<String, Object> model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Componente> componentes = componenteService.findAll(pageRequest);
		
		PageRender<Componente> pageRender = new PageRender<Componente>("/lista", componentes);
				
		model.put("componentes", componentes);
		model.put("page", pageRender);

		return "index";
	}
	
	
	@RequestMapping(value = "/componentes/form")
	public String crear(Map<String, Object> model) {

		Componente componente = new Componente();
		model.put("componente", componente);
		model.put("titulo", "Nuevo Componente para BAME");
		model.put("boton", "Guardar");

		return "formComponente";
	}
	
	@RequestMapping(value = "/componentes/form", method = RequestMethod.POST)
	public String guardar(Componente componente, 
			BindingResult result, 
			Model model,
			RedirectAttributes flash,
			SessionStatus status) {
		
		
		
		componente.setInstrumento(instrumentoService.findById((Long)componente.getInstrumento().getId()));
		
		//System.out.println(componente.toString());
		
		Componente componenteSave = componenteService.save(componente);
		
		status.setComplete();
		
		String mensaje="";
		if(componente.getId() != null) {
			
			mensaje = "Se ha actualizado el componente " + componenteSave.getNombre() 
			+ " " + componenteSave.getApellido() +" correctamente";
			
		}else {
			
			mensaje = "Se ha creado el componente " + componenteSave.getNombre() 
			+ " " + componenteSave.getApellido() +" correctamente";
		}
		flash.addFlashAttribute("success",mensaje);
		
		return "redirect:/lista";
	}
	
	
	@RequestMapping(value = "/componentes/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash)
	{
		
		
		Componente c = null;
		if (id > 0) {

			c = componenteService.findById(id);
			/*
			 * Si el id que se le pasa no esta en la BBDD, devolverá un c nulo
			 */
			if (c == null) {

				flash.addFlashAttribute("error", "El componente no exixte en la BBDD");
				return "redirect:/listar";
			}

		} else {
			flash.addFlashAttribute("error", "El componente no puede editarse");
			return "redirect:/listar";
		}

		model.put("componente", c);
		model.put("titulo", "Editar componente");
		//model.put("boton", "Editar");

		return "formComponente";
	}
	
	
	@RequestMapping(value = "componentes/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, 
			RedirectAttributes flash) {
		
				if(id>0) {
					
					Componente c = componenteService.findById(id);
					componenteService.delete(c);
					flash.addFlashAttribute("success", "Cliente eliminado con éxito");
					
				}else {
					
					flash.addFlashAttribute("error", "El cliente no se puede eliminar");
				}
		
				return "redirect:/";
	}
	
	@RequestMapping(value = "componentes/ver/{id}", method = RequestMethod.GET)
	public String verComponente(@PathVariable(value = "id") Long id,Map<String, Object> model, RedirectAttributes flash) {
		
		Componente componente = componenteService.findById(id);
		 if( componente == null) {
			 
			 
			 flash.addFlashAttribute("error", "No se puede ver el detalle del componente");
			 return "redirect:/lista";
		 }
		
		 model.put("titulo", "Detalle del Componente : " + componente.getNombre());
		 model.put("componente", componente);
		
		return "verComponente";
	}
	
	
	@RequestMapping(value = "/listado/pdf")
	public String detallePdf(HttpServletRequest request, HttpServletResponse response)  {
		
		
			
			List<Componente> componentes = componenteService.findAll();
			
			if( componentes != null && !componentes.isEmpty()) {
				
				reporteService.generateReportComponente(componentes);
				
				try {
			         byte[] documentInBytes = reporteService.mostrar("reporte_lista_componentes.pdf");       
			         //response.setHeader("Content-Disposition", "inline; filename=reporte de prestamo");
			         response.setDateHeader("Expires", -1);
			         response.setContentType("application/pdf");
			         response.setContentLength(documentInBytes.length);
			         response.getOutputStream().write(documentInBytes);
			     } catch (Exception ioe) {
			     } finally {
			     }
			}
			

		return null;
	}
		


}
