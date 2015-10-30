package br.edu.ifba.bsi.embarcados.regua.asincexec;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.bsi.embarcados.regua.conector.FabricaConectores;
import br.edu.ifba.bsi.embarcados.regua.conector.IComunicacaoSensores;

public class AsincExec implements Runnable {

	private boolean continuar;
	private String porta;

	private List<IListenerAcelerometro> listeners;

	public AsincExec(String porta) {
		this.porta = porta;
		listeners = new ArrayList<IListenerAcelerometro>();
	}

	public void addListener(IListenerAcelerometro listener) {
		listeners.add(listener);
	}

	public void setContinuar(boolean continuar) {
		this.continuar = continuar;
	}

	@Override
	public void run() {
		IComunicacaoSensores conector = FabricaConectores.getConector();
		if(conector.iniciar(porta) == 0){
			continuar = true;
			while(continuar){
				conector.ler();
				//notificar os angulos.
				notificar(conector.getAnguloXZ(), conector.getAnguloYZ());
			}
			conector.finalizar();
		}

	}
	
	private void notificar(double xz, double yz){
		for(IListenerAcelerometro listener: listeners){
			listener.notificarInclinacao(xz, yz);
		}
	}

}
