package br.edu.ifba.bsi.embarcados.regua;

import br.edu.ifba.bsi.embarcados.regua.asincexec.AsincExec;
import br.edu.ifba.bsi.embarcados.regua.asincexec.IListenerAcelerometro;
import br.edu.ifba.bsi.embarcados.regua.grafico.Dialog;

public class Executor {

	public static void main(String[] args) throws InterruptedException {
		AsincExec asincExec = new AsincExec("COM6");
		
		Dialog dialog = new Dialog();
		

		IListenerAcelerometro listener = dialog.getListener();
		
		asincExec.addListener(listener);
		
		Thread t  = new Thread(asincExec);
		t.start();

		dialog.init();
		
//		for(int i=0;i<10;i++){
//			Thread.sleep(1000); //thread principal
//		}
		
		asincExec.setContinuar(false);
		t.join();
	}
	
}