package com.bame.es.gestion.app.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bame.es.gestion.app.models.entity.Marcha;
import com.bame.es.gestion.app.models.entity.Voz;
import com.bame.es.gestion.app.models.service.impl.IMarchaService;
import com.bame.es.gestion.app.models.service.impl.IUploadFileService;

@Controller
@RequestMapping(value = "/voz")
@SessionAttributes("voz")
public class VozController {
	
	@Autowired
	private IMarchaService marchaService;
	
	@Autowired
	private IUploadFileService uploadService;

	@RequestMapping(value = "/form/{id}")
	public String crear(@PathVariable(name = "id") Long id, Map<String, Object> model,RedirectAttributes flash) {
		
		Marcha marcha = marchaService.findById(id);
		
		if(marcha == null) {
			
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/repertorio/lista";
		}
		
		Voz nuevaVoz = new Voz();
		nuevaVoz.setMarcha(marcha);
		model.put("titulo", "Nueva voz para marcha " + marcha.getNombre());
		model.put("voz", nuevaVoz);
		model.put("marcha", marcha);
		
		return "voz/formVoz";
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid Voz voz,
			BindingResult result,
			RedirectAttributes flash, 
			@RequestParam("file") MultipartFile partitura, 
			SessionStatus session,
			Map<String, Object> model) {
		
			if(result.hasErrors() || 
					partitura.isEmpty() || 
					!partitura.getContentType().equals("application/pdf")) {
				
				if(partitura.isEmpty()) {
					
					String[] codes =null;
					Object[] arg = null;
					FieldError flderr = new FieldError("voz", "partitura", 
							voz.getPartitura(), false, codes, arg, 
							"Seleccione partitura por favor");
					
					result.addError(flderr);
				}
				model.put("marcha", voz.getMarcha());
				return "voz/formVoz";
			}
			
			if(!partitura.isEmpty()) {
				
				String uniqueFileName = null;
				try {
					uniqueFileName = uploadService.copy(partitura);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				// Pasamos un mensaje flash a la vista
				flash.addFlashAttribute("info", "Ha subido correctamente la partitura para " + voz.getNombre());

				// Pasamos el nombre original del pdf de la partitura
				voz.setPartitura(uniqueFileName);
				
			}
			
			
			
			marchaService.saveVoz(voz);
			session.setComplete();
		
			flash.addFlashAttribute("success", "la voz de " + voz.getNombre() + "se ha guardado correctamente");
			return "redirect:/repertorio/ver/" + voz.getMarcha().getId();
	}
	
	
	 @RequestMapping(value = "/pdf/{filename:.+}", method = RequestMethod.GET)
	 protected String preivewSection(@PathVariable String filename,   
	     HttpServletRequest request,
	     HttpServletResponse response,
	     RedirectAttributes flash) {
		 
		 
		 //System.out.println(filename);
		 byte[] documentInBytes = uploadService.mostrar(filename);
		 
		 if(documentInBytes == null) {
			 
				flash.addFlashAttribute("error", "No se puede mostrar la imagen en este momento");
				return "redirect:/voz/formVoz";
		 }else {
		 
	     try {
	          documentInBytes = uploadService.mostrar(filename);       
	         //response.setHeader("Content-Disposition", "inline; filename=\"report.pdf\"");
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
