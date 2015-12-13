package br.edu.ifba.ls.lightsaver;

import com.sun.jna.Library;

public interface IComunicacaoRF extends Library{
	
	int iniciar(String porta);
	int ler();
	int getId();
	int getLuminosidade();
	int getTemperatura();
	int getDistancia();
	int finalizar();

}
