package br.edu.ifba.ls.lightsaver;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.edu.ifba.ls.lightsaver.conector.SingleConector;

@ManagedBean(name="monitor")
public class Monitor {
	
	private int luminosidade;
	private int temperatura;
	private int distancia;
	
	@PostConstruct
	public void iniciar(){
		lerSensores();
	}
	
	public void lerSensores(){
		//acionar a leitura do arduino
		Informacao info = SingleConector.getInformacao();
		
		this.luminosidade = info.getLuminosidade();
		this.temperatura = info.getTemperatura();
		this.distancia = info.getDistancia();
		
		
		System.out.println("Temperatura: "+temperatura+", Distância: "+distancia+", Luminosidade: "+luminosidade);
		//atualizar os valores nos medidores
	}

	public int getLuminosidade() {
		return luminosidade;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setLuminosidade(int luminosidade) {
		this.luminosidade = luminosidade;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	
		

}
