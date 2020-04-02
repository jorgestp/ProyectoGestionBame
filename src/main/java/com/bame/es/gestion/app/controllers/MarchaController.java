package com.bame.es.gestion.app.controllers;

import java.io.IOException;
//import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bame.es.gestion.app.models.entity.Marcha;
import com.bame.es.gestion.app.models.entity.TipoMarcha;
import com.bame.es.gestion.app.models.service.impl.IMarchaService;
import com.bame.es.gestion.app.models.service.impl.ITipoMarchaService;
import com.bame.es.gestion.app.models.service.impl.IUploadFileService;
import com.bame.es.gestion.app.pageRender.PageRender;



@Controller
@RequestMapping(value = "/repertorio")
@SessionAttributes("marcha")
public class MarchaController {
	
	
	@Autowired
	private IMarchaService marchaService;
	
	@Autowired
	private ITipoMarchaService tipomarchaService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	
	
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
			@RequestParam("file") MultipartFile guia,
			RedirectAttributes flash,
			SessionStatus status) {
		
		///////////////////////////////////////////////////////////
		
		//Si la foto no esta vacia, entra
		if(!guia.isEmpty()) {
			
			/*
			 * Preguntamos si el id de la marcha es distinto de nulo, señal entonces de
			 * que estamos modificando. Ademas preguntamos que sea el id mayor a 0.
			 * Tambien, que el atributo de la marcha, foto, no este vacio y no sea null.
			 * 
			 * Si ocurre todo eso, borramos la imagen del directorio
			 */
			if(marcha.getId() != null &&
					marcha.getId()>0 &&
					marcha.getGuia().length() > 0 &&
					marcha.getGuia() != null) {
				
					uploadService.delete(marcha.getGuia());
				
			}
			
			String uniqueFileName = null;
			try {
				uniqueFileName = uploadService.copy(guia);
			} catch (IOException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
			
			// Pasamos un mensaje flash a la vista
			flash.addFlashAttribute("info", "Ha subido correctamente el guion");

			// Pasamos el nombre original de la foto a la marcga
			marcha.setGuia(uniqueFileName);
			
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
	
	 @RequestMapping(value = "/pdf/{filename:.+}", method = RequestMethod.GET)
	 protected String preivewSection(@PathVariable String filename,   
	     HttpServletRequest request,
	     HttpServletResponse response) {
		 
		 
		 System.out.println(filename);
		 
	     try {
	         byte[] documentInBytes = uploadService.mostrar(filename);       
	         //response.setHeader("Content-Disposition", "inline; filename=\"report.pdf\"");
	         response.setDateHeader("Expires", -1);
	         response.setContentType("application/pdf");
	         response.setContentLength(documentInBytes.length);
	         response.getOutputStream().write(documentInBytes);
	     } catch (Exception ioe) {
	     } finally {
	     }
	     return null;
	 }
	
	

}
