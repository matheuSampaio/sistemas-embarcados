package br.edu.ifba.bsi.embarcados.regua;

import br.edu.ifba.bsi.embarcados.regua.asincexec.IListenerAcelerometro;

public class ListenerAcelerometro implements IListenerAcelerometro {

	@Override
	public void notificarInclinacao(double xz, double yz) {
		System.out.println("XZ = " + xz);
		System.out.println("YZ = " + yz);

	}

}
