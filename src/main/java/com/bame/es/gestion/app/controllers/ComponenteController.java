package com.bame.es.gestion.app.controllers;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bame.es.gestion.app.models.entity.Componente;
import com.bame.es.gestion.app.models.service.impl.IComponenteService;
import com.bame.es.gestion.app.models.service.impl.IInstrumentoService;
import com.bame.es.gestion.app.models.service.impl.IUploadFileService;
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
	
	@Autowired
	private IUploadFileService uploadService;
	

	
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
	public String guardar(@Valid Componente componente, 
			BindingResult result, 
			Map<String, Object> model,
			RedirectAttributes flash,
			@RequestParam("file") MultipartFile foto,
			SessionStatus status) {
		
		if(result.hasErrors() || componente.getInstrumento().getId() == null) {
			
			
			if(componente.getInstrumento().getId() == null) {
				
				String[] codes =null;
				Object[] arg = null;
				FieldError flderr = new FieldError("componente", "instrumento.id", 
						componente.getFoto(), false, codes, arg, 
						"Debe seleccionar un instrumento");
				
				result.addError(flderr);
			}
			
			model.put("titulo", "Nuevo Componente para BAME");
			model.put("boton", "Guardar");

			return "formComponente";
		}
		
		
		//Si la foto no esta vacia, entra
		if(!foto.isEmpty()) {
			
			/*
			 * Preguntamos si el id de la marcha es distinto de nulo, señal entonces de
			 * que estamos modificando. Ademas preguntamos que sea el id mayor a 0.
			 * Tambien, que el atributo de la marcha, foto, no este vacio y no sea null.
			 * 
			 * Si ocurre todo eso, borramos la imagen del directorio
			 */
			if(componente.getId() != null &&
					componente.getId()>0 &&
					componente.getFoto().length() > 0 &&
					componente.getFoto() != null) {
				
					uploadService.delete(componente.getFoto());
				
			}
			
			String uniqueFileName = null;
			try {
				uniqueFileName = uploadService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
			
			// Pasamos un mensaje flash a la vista
			flash.addFlashAttribute("info", "Ha subido correctamente la foto correctamente");

			// Pasamos el nombre original de la foto a la marcga
			componente.setFoto(uniqueFileName);
			
		}
		
		
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
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
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
