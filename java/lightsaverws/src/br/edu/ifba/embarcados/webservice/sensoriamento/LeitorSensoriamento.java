package br.edu.ifba.embarcados.webservice.sensoriamento;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeitorSensoriamento implements Runnable {

	private static final int DESLOCAMENTO_RFID  = 24;
	private static final int DESLOCAMENTO_TEMP = 16;
	private static final int DESLOCAMENTO_LUMIN = 8;
	
	// referencia/acesso estatico a sensores
	private static Integer sensores = 0;

	public static int getSensores() {
		synchronized (sensores) {
			return sensores;
		}
	}
	
	public static int getRFID() {
		int id = getSensores();
		
		id = id >> DESLOCAMENTO_RFID;
		
		return id;
	}
	
	public static int getLuminosidade() {
		int lumin = getSensores();
		
		lumin = (lumin & 16777215) >> DESLOCAMENTO_TEMP;
		
		return lumin;
	}
	
	public static int getTemperatura() {
		int temp = getSensores();
		
		temp = (temp & 65535) >> DESLOCAMENTO_LUMIN;
		
		return temp;
	}
	
	public static int getDistancia() {
		int dist = getSensores();
		
		dist = (dist & 255);
		
		return dist;
	}

	// acesso a arquivo PIPE de sensoriamento
	private static final String ARQUIVO_PIPE = "/home/matheus/Documents/sistemas-embarcados/cplusplus/sensoriamento/sensor";
	private RandomAccessFile fifo = null;

	private boolean continuar = true;

	public LeitorSensoriamento() {
		try {
			fifo = new RandomAccessFile(ARQUIVO_PIPE, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		continuar = true;
		while (continuar) {
			String s = "";
			try {
				if (((s = fifo.readLine()) != null) && !s.equals("")) {
					synchronized (sensores) {
							sensores = Integer.parseInt(s);
					}

					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			fifo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parar() {
		continuar = false;
	}
	
}
