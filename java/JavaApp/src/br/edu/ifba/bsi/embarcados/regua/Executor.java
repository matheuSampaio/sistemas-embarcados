package br.edu.ifba.bsi.embarcados.regua;

import br.edu.ifba.bsi.embarcados.regua.conector.FabricaConectores;
import br.edu.ifba.bsi.embarcados.regua.conector.IComunicacaoSensores;

public class Executor {

	public static void main(String[] args) throws InterruptedException {
		IComunicacaoSensores conector = FabricaConectores.getConector();
		if(conector.iniciar("COM6")==0){//sucesso
			while(true){
				conector.ler();
//				System.out.println("AcelX = "+conector.getAcelX());
//				System.out.println("AcelY = "+conector.getAcelY());
//				System.out.println("AcelZ = "+conector.getAcelZ());
				
				System.out.println("Angulo YZ:"+ (int) conector.getAnguloYZ());
				System.out.println("Angulo XZ:"+ (int) conector.getAnguloXZ());
				
				Thread.sleep(100);
			}
		}
	}
}
