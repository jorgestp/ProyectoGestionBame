package com.bame.es.gestion.app.pageRender;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

/*
 * Clase que permite acotar de manera dinamica la mostracion en la vista del
 * numero de clientes por pagina, de modo que en la pagina siguiente a 
 * una actual, se cargen los registros adecuados.
 * Se coloca el tipo Generico T
 */
public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPaginas;
	private int numeroElementosPorPagina;
	private int paginaActual;
	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;

		this.paginas = new ArrayList<PageItem>();

		//Almacena el numero de elementos por pagina
		numeroElementosPorPagina = page.getSize();
		//almacena el numero total de paginas que tiene el Page en total
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1; // pq comienza en 0

		/*
		 * Aqui empezamos a calcular los parametros DESDE y HASTA para empezar a dibujar
		 * el paginador en la vista
		 */
		int desde, hasta;
		// Si el total de pagina es menor o igual al numero de pagina
		if (totalPaginas <= numeroElementosPorPagina) {

			desde = 1;
			hasta = totalPaginas;
		} else {

			if (paginaActual <= numeroElementosPorPagina / 2) {

				desde = 1;
				hasta = numeroElementosPorPagina;
			} else if (paginaActual >= totalPaginas - numeroElementosPorPagina / 2) {

				desde = totalPaginas - numeroElementosPorPagina + 1;
				hasta = numeroElementosPorPagina;
			} else {

				desde = paginaActual - numeroElementosPorPagina / 2;
				hasta = numeroElementosPorPagina;
			}
		}

		for (int i = 0; i < hasta; i++) {

			paginas.add(new PageItem(desde + i, paginaActual == desde + i));
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public int getNumeroElementosPorPagina() {
		return numeroElementosPorPagina;
	}

	public void setNumeroElementosPorPagina(int numeroElementosPorPagina) {
		this.numeroElementosPorPagina = numeroElementosPorPagina;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PageItem> paginas) {
		this.paginas = paginas;
	}
	
	public boolean isFirst() {
		
		return page.isFirst();	
	}

	
	public boolean isLast() {
		
		return page.isLast();
	}
	
	public boolean isHasNext() {
		
		return page.hasNext();
		
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
