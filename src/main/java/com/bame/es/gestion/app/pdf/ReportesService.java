package com.bame.es.gestion.app.pdf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.bame.es.gestion.app.models.entity.ItemPrestamo;
import com.bame.es.gestion.app.models.entity.Material;
import com.bame.es.gestion.app.models.entity.Prestamo;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportesService {
	
	public void generateReport(Prestamo prestamo) {
		
			
		
		try {

			List<Material> materiales = new ArrayList<Material>();
			
			for ( ItemPrestamo item : prestamo.getItemsPrestamo()) {
				
				materiales.add(item.getMaterial());
			}
			

			//String reportPath = "F:\\Content\\Report";
			String jas = "reporte_detalle_prestamo.jrxml";
			
			Path ruta = Paths.get("src/main/resources/jasper").resolve(jas).toAbsolutePath();
			
			System.out.println(ruta.toString());
			File file = ResourceUtils.getFile(ruta.toString());
			
			System.out.println(file.getAbsolutePath().toString());

			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager
					.compileReport(file.getAbsolutePath());

			// Get your data source
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(materiales);

			// Add parameters
			Map<String, Object> parameters = new HashMap<>();

			parameters.put("nombreComponente", prestamo.getComponente().getNombre() + " " + 
			prestamo.getComponente().getApellido());
			parameters.put("direccion", prestamo.getComponente().getDireccion());
			parameters.put("dni", prestamo.getComponente().getDni());
			parameters.put("instrumento", prestamo.getComponente().getInstrumento().getNombre());
			parameters.put("descripcion", prestamo.getDescripcion());
			parameters.put("observacion", prestamo.getObservacion());
			parameters.put("fecha", prestamo.getCreateAt());

			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					jrBeanCollectionDataSource);
			
			Path guardar = Paths.get("src/main/resources/jasper").toAbsolutePath();
			// Export the report to a PDF file
			//JasperExportManager.exportReportToPdfFile(jasperPrint, guardar.toString());
			System.out.println(guardar.toString());

			JasperExportManager.exportReportToPdfFile(jasperPrint, guardar.toString()+ "\\reporte_propio1.pdf");
			//JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			
			System.out.println(guardar.toString()+ "\\reporte_propio1.pdf");
			
			 
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public byte[] mostrar(String filename) {
		
		byte[] b = null;
		
		Path pathFoto = getPath(filename);
		
		try {
			b = Files.readAllBytes(pathFoto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	public Path getPath(String filename) {

		return Paths.get("src/main/resources/jasper").resolve(filename).toAbsolutePath();
		
	}

}
