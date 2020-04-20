package com.bame.es.gestion.app.controllers;




import java.util.List;
import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.bame.es.gestion.app.models.entity.Componente;
import com.bame.es.gestion.app.models.entity.ItemPrestamo;
import com.bame.es.gestion.app.models.entity.Material;
import com.bame.es.gestion.app.models.entity.Prestamo;
import com.bame.es.gestion.app.models.service.impl.IComponenteService;
import com.bame.es.gestion.app.pdf.ReportesService;



@Controller
@RequestMapping(value = "/prestamo")
@SessionAttributes("prestamo")
public class PrestamoController {
	
	@Autowired
	private IComponenteService componenteService;

	@Autowired
	private ReportesService reporteService;
	
	@RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
	public String crearPrestamo(@PathVariable(name = "id") Long id, 
			Map<String, Object> model,
			RedirectAttributes flash) {
		
			Componente componente = componenteService.findById(id);
			
			if(componente == null) {
				flash.addFlashAttribute("error", "El componente no existe");
				return "redirect:/repertorio/lista";
			}
			
			Prestamo prestamo = new Prestamo();
			prestamo.setComponente(componente);
			List<Material> materiales = componenteService.findMateriales();
			
			model.put("titulo", "Prestamo para " + componente.getNombre());
			model.put("prestamo", prestamo);
			model.put("materiales", materiales);
			
			System.out.println(materiales.size());
		
		return "prestamo/formPrestamo";
	}
	
	@GetMapping(value = "/cargar-productos/{id}", produces = {"application/json"})
	public @ResponseBody Material cargarProductos (@PathVariable Long id){
		
		return componenteService.findMaterialById(id);
	}
	
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid Prestamo prestamo,
			BindingResult result,
			Map<String, Object> model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			RedirectAttributes flash, 
			SessionStatus session) {
		
			if(result.hasErrors()) {
				
				model.put("titulo", "Prestamo para " + prestamo.getComponente().getId());
				
				List<Material> materiales = componenteService.findMateriales();
				model.put("materiales", materiales);
				
				return "prestamo/formPrestamo";
			}
		
			
			//Debe ir primero el null. Sino, en el caso de que el array sea nulo, darar error teniendo el lenght el primero
			if(itemId == null || itemId.length == 0 ) {
				
				model.put("titulo", "Prestamo para " + prestamo.getComponente().getId());
				model.put("error","Debe incluir alguna linea en el prestamo");
				List<Material> materiales = componenteService.findMateriales();
				model.put("materiales", materiales);
				
				return "prestamo/formPrestamo";
			}
			
			for(int i = 0; i < itemId.length; i++) {
				
				Material material = componenteService.findMaterialById(itemId[i]);
				ItemPrestamo item = new ItemPrestamo();
				item.setMaterial(material);
				prestamo.addItemsPrestamo(item);
			}
			
			
			componenteService.savePrestamo(prestamo);
			session.setComplete();
		
			flash.addFlashAttribute("success", "Se ha guardado el prestamo correctamente");
			return "redirect:/componentes/ver/" + prestamo.getComponente().getId();
	}
	
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable(value = "id") Long id,
			RedirectAttributes flash) {
		
		Prestamo prestamo = componenteService.findPrestamoById(id);
		
		if(prestamo != null) {
			
			componenteService.deletePrestamoById(id);
			
			flash.addFlashAttribute("success", "El prestamo ha sido eliminado del componente "
			+ prestamo.getComponente().getNombre());
			
			return "redirect:/componentes/ver/" + prestamo.getComponente().getId();
			
		}
		
		flash.addFlashAttribute("error", "No se puede eliminar el prestamo de la base de datos");
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
	public String verPrestamo(@PathVariable(value = "id") Long id,
			Map<String, Object> model,
			RedirectAttributes flash) {
		
		Prestamo prestamo = componenteService.findPrestamoCompleto(id);
		
		if(prestamo != null) {
			
			model.put("prestamo", prestamo);
			model.put("titulo", "Detalle de prestamo " + prestamo.getDescripcion());
			
			return "prestamo/detallePrestamo";
			
		}
		
		flash.addFlashAttribute("error", "El prestamo no se puede mostrar o no existe en la BBDD");
		
		return "redirect:/lista";
	}
	
	@RequestMapping(value = "/detalle/pdf/{id}")
	public String detallePdf(@PathVariable(value = "id") Long id)  {
		
		if(id>0) {
			
			Prestamo prestamo = componenteService.findPrestamoCompleto(id);
			
			if( prestamo != null) {
				
				reporteService.generateReport(prestamo);
			}
			
		}
		

		
		return null;
	}

}
