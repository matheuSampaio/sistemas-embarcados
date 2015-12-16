package br.edu.ifba.ls.lightsaver;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.MeterGaugeChartModel;

import br.edu.ifba.ls.lightsaver.conector.SingleConector;

@ManagedBean(name="monitor")
public class Monitor {
	
	private int luminosidade;
	private int temperatura;
	private int distancia;
	
	private MeterGaugeChartModel medidorLuminosidade;
	private MeterGaugeChartModel medidorTemperatura;
	private MeterGaugeChartModel medidorDistancia;
	
	@PostConstruct
	public void iniciar(){
		configurarMedidores();
//		lerSensores();
	}
	
	public void lerSensores(){
		//acionar a leitura do arduino
		Informacao info = SingleConector.getInformacao();
		
		this.luminosidade = info.getLuminosidade();
		this.temperatura = info.getTemperatura();
		this.distancia = info.getDistancia();
		
		
		System.out.println("Temperatura: "+temperatura+", Distância: "+distancia+", Luminosidade: "+luminosidade);
		
		//atualizar os valores nos medidores
		
		medidorLuminosidade.setValue(luminosidade);
		medidorTemperatura.setValue(temperatura);
		medidorDistancia.setValue(distancia);
	}
	
	private void configurarMedidores(){

		medidorLuminosidade = criarMedidor(150, 10);
		medidorTemperatura = criarMedidor(50,10);
		medidorDistancia = criarMedidor(250, 10);
		
		medidorLuminosidade.setTitle("Luminosidade");
		medidorLuminosidade.setGaugeLabel("OHM");
		
		medidorTemperatura.setTitle("Temperatura");
		medidorTemperatura.setGaugeLabel("ºC");
		
		medidorDistancia.setTitle("Distância do objeto");
		medidorDistancia.setGaugeLabel("cm");
	}
	
	private MeterGaugeChartModel criarMedidor(int max, int intervalo){
		List<Number> marcadores = new ArrayList<Number>();

		for(int i=0;i<=max; i+=intervalo){
			marcadores.add(i);
		}
		return new MeterGaugeChartModel(0, marcadores);
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
	public MeterGaugeChartModel getMedidorDistancia() {
		return medidorDistancia;
	}
	public MeterGaugeChartModel getMedidorLuminosidade() {
		return medidorLuminosidade;
	}
	public MeterGaugeChartModel getMedidorTemperatura() {
		return medidorTemperatura;
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
	public void setMedidorDistancia(MeterGaugeChartModel medidorDistancia) {
		this.medidorDistancia = medidorDistancia;
	}
	public void setMedidorLuminosidade(MeterGaugeChartModel medidorLuminosidade) {
		this.medidorLuminosidade = medidorLuminosidade;
	}
	public void setMedidorTemperatura(MeterGaugeChartModel medidorTemperatura) {
		this.medidorTemperatura = medidorTemperatura;
	}
	
		

}
