package br.edu.ifba.bsi.embarcados.regua;

import br.edu.ifba.bsi.embarcados.regua.asincexec.AsincExec;
import br.edu.ifba.bsi.embarcados.regua.conector.FabricaConectores;
import br.edu.ifba.bsi.embarcados.regua.conector.IComunicacaoSensores;

public class Executor {

	public static void main(String[] args) throws InterruptedException {
		ListenerAcelerometro listener = new ListenerAcelerometro();
		AsincExec asinc = new AsincExec("COM6");
		asinc.addListener(listener);

		Thread t = new Thread(asinc);
		t.start();

		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
		}
		// parar a thread
		asinc.setContinuar(false);
		// ter certeza que parou
		t.join();
	}
}
