




$(document).ready(function() {
	loop();
});

function loop() {
	
	mudaClaridade();
	ligaDesligaTV();
	
    window.setTimeout(function() { loop() }, 500);
}

function mudaClaridade(){
	var opacidade =  $("[id*='luminosidade']").val();
	var claridade = 120 - opacidade;
	if(claridade < 0) claridade = 0;
	if(claridade > 100) claridade = 99;
	$("body").attr("style", "background: rgba(21, 21, 21, 0."+ claridade +");");
	
	if(opacidade > 89) opacidade = 89; 
	if(opacidade < 11) opacidade = 11;
	$(".luz").css("opacity", "0."+opacidade);
	$(".luz2").css("opacity", "0."+opacidade);
}

function ligaDesligaTV(){
	var distancia =  $("[id*='distancia']").val();
	if(distancia < 200){
		$("#naruto").attr("src", "img/desligada.png");
	}else if($("#naruto").attr("src") != "img/naruto.gif"){
		$("#naruto").attr("src", "img/naruto.gif");
	}
}