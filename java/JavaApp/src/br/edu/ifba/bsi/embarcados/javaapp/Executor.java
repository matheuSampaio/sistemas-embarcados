package br.edu.ifba.bsi.embarcados.javaapp;

import br.edu.ifba.bsi.embarcados.javaapp.concetor.FabricaConectores;
import br.edu.ifba.bsi.embarcados.javaapp.concetor.IComunicacaoSensores;

public class Executor {

	public static void main(String[] args) throws InterruptedException {
		IComunicacaoSensores conector = FabricaConectores.getConector();
		if(conector.iniciar("COM6")==0){//sucesso
			while(true){
				conector.ler();
				System.out.println("AcelX = "+conector.getAcelX());
				System.out.println("AcelY = "+conector.getAcelY());
				System.out.println("AcelZ = "+conector.getAcelZ());
				
				Thread.sleep(100);
			}
		}
	}
}
