package br.edu.ifba.ls.lightsaver.leitor;

import br.edu.ifba.ls.lightsaver.conector.SingleConector;

public class LeitorAssincrono implements Runnable{

	private boolean continuar;
	
	
	public LeitorAssincrono() {
	}
	
	@Override
	public void run() {
		continuar = true;
		
		while(continuar){
			SingleConector.ler();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void parar(){
		continuar = false;
	}

}
