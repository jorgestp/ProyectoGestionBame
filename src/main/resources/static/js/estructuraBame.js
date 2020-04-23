/**
 * 
 */

$(document).ready(function(){

    //alert("BODY " + $("body").height());
    //alert("window " + $(window).height());

    //Si el alto del body es menor que el alto del window
    if($("body").height() < $(window).height()){

       //alert("body menor que windows");

        /*Le estams diciendo que el footer tenga una posicion absoluta y tenga un espacio por abajo de 0px
        */
        $("footer").css({"position":"absolute", "bottom":"0px"});
    }else{

        //alert("body mayor que windows");

        $("footer").css({"bottom":"0px"});
    }


});