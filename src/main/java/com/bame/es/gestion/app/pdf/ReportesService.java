package com.bame.es.gestion.app.pdf;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.bame.es.gestion.app.models.entity.Employee;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportesService {
	
	public void generateReport() {
		
			
		
		try {

			List<Employee> employees = new ArrayList<Employee>();
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			employees.add(new Employee(1L, "Jorge"));
			employees.add(new Employee(2L, "Cristina"));
			employees.add(new Employee(3L, "Samuel"));
			employees.add(new Employee(4L, "Ana"));
			

			//String reportPath = "F:\\Content\\Report";
			String jas = "report1.jrxml";
			
			Path ruta = Paths.get("src/main/resources/jasper").resolve(jas).toAbsolutePath();
			
			System.out.println(ruta.toString());
			File file = ResourceUtils.getFile(ruta.toString());
			
			System.out.println(file.getAbsolutePath().toString());

			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager
					.compileReport(file.getAbsolutePath());

			// Get your data source
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(employees);

			// Add parameters
			Map<String, Object> parameters = new HashMap<>();

			parameters.put("nombreComponente", "Websparrow.org");

			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					jrBeanCollectionDataSource);
			
			Path guardar = Paths.get("src/main/resources/jasper").toAbsolutePath();
			// Export the report to a PDF file
			//JasperExportManager.exportReportToPdfFile(jasperPrint, guardar.toString());
			System.out.println(guardar.toString());

			JasperExportManager.exportReportToPdfFile(jasperPrint, guardar.toString()+ "\\reporte_propio.pdf");
			//JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			
			System.out.println(ruta.toString()+ "\\reporte.pdf");
			
			 

		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
