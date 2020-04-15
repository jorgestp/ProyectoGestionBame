/**
 * 
 */

$(document).ready(function() {
			
	$("#id_seleccion").on('change', '#seleccion', function(event) {
	     alert("EL VALOR SELECCIONADO ES "+ $("#seleccion").val());
	});
});