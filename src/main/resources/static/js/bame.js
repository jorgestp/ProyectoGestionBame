/**
 * 
 */

$(document).ready(function() {
	
	var id ="";
	var nombre="";
	$("#id_seleccion").on('change', '#seleccion', function(event) {
	     //alert("EL VALOR SELECCIONADO ES "+ $("#seleccion").val());
	     
			$.get( "/prestamo/cargar-productos/" + $("#seleccion").val(), function( data ) {
				$("#seleccion").val("");
				  //alert( "Load was performed." );
				  //alert(JSON.stringify(data));
				  
				  //alert("VALOR DE DATA NOMBRE ES " + data["nombre"]);
				  //alert("VALOR DE DATA NOMBRE ES " + data["id"]);

				  id=data["id"];
				  nombre=data["nombre"];
				  //alert(id);
				  //alert(nombre);
				  
				  if(confirm("Añadir linea?")== 1){
					  
						var linea = $("#plantillaItemsMaterial").html();
						
						//alert("VALOR DE LA LINEA " + linea)
						
						linea = linea.replace(/{ID}/g, id);
						linea = linea.replace(/{NOMBRE}/g, nombre);
						
						//alert("VALOR DE LA LINEA " + linea);

						$("#cargarItemProductos tbody").append(linea);
					  
				  }


				});
			

	});
	

});