package br.edu.ifba.embarcados.lightsaverclient;

import br.edu.ifba.embarcados.lightsaverclient.conector.Conector;

public class Executor {

	public static void main(String[] args) throws InterruptedException {
		Conector conector = new Conector();

		while (true) {
			Integer id = conector.getId();
			System.out.println("id = " + id);
			
			System.out.println("Sensores...");
			
			Integer luminosidade = conector.getLuminosidade();
			Integer temperatura = conector.getTemperatura();
			Integer distancia = conector.getDistancia();
			System.out.println("Luminosidade: " + luminosidade+
											" | Temperatura: "+temperatura + 
											" | Distância do objeto = " + distancia);
			
			System.out.println("Final de leitura!");
			System.out.println("...");
			
			
			Thread.sleep(1000);
		}
	}

}
