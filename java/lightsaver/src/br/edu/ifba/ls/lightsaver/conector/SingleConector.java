package br.edu.ifba.ls.lightsaver.conector;

import java.util.concurrent.Semaphore;

import br.edu.ifba.ls.lightsaver.IComunicacaoRF;
import br.edu.ifba.ls.lightsaver.Informacao;

public class SingleConector {
	
	private static final String PORTA = "COM6";
	private static IComunicacaoRF comRF = null; //fica disponível enquanto a JVM estiver no ar
	private static Informacao info;
	private static Semaphore semaforo;
	
	public static void iniciarComunicacoRF(String libPath){
		info = new  Informacao();
		comRF = FabricaConectores.getConector(libPath);
		
		if (comRF.iniciar(PORTA)==0){
			System.out.println("Acesso a sensores iniciado com sucesso.");
			dispensarPrimeirasLeituras();
			semaforo = new Semaphore(1, true);
		}else
			System.out.println("Nao foi possível iniciar acesso a sensores.");
	}
	
	public static void dispensarPrimeirasLeituras(){
		for(int i=0; i<10; i++){
			comRF.ler();
			System.out.println("Dispensando leitura [L/T/D]: "+comRF.getLuminosidade() +"/"+comRF.getTemperatura()+"/"+comRF.getDistancia());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Informacao getInformacao(){
		Informacao info_ = new Informacao();
		
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		info_.setLuminosidade(info.getLuminosidade());
		info_.setTemperatura(info.getTemperatura());
		info_.setDistancia(info.getDistancia());
		semaforo.release();
		
		return info_;
	}
	
	public static int ler(){
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int resultado = comRF.ler();
		
		if(resultado == 0){
			info.setLuminosidade(comRF.getLuminosidade());
			info.setTemperatura(comRF.getTemperatura());
			info.setDistancia(comRF.getDistancia());
		}
		
		semaforo.release();
		
		return resultado;
	}
	
	public static void  finalizar(){
		comRF.finalizar();
	}
}
