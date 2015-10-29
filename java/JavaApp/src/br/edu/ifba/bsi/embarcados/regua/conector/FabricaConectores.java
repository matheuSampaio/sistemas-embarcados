package br.edu.ifba.bsi.embarcados.regua.conector;


import com.sun.jna.Native;
import com.sun.jna.Platform;

public class FabricaConectores {
	
	public static IComunicacaoSensores getConector(){
		IComunicacaoSensores conector = null;
		
		if(Platform.isWindows()){
			//procura os métodos no dll e passa pra interface
			conector = (IComunicacaoSensores) Native.loadLibrary("comunicaosensores.dll", IComunicacaoSensores.class); 
		}else if(Platform.isLinux()){
			conector = (IComunicacaoSensores) Native.loadLibrary("comunicaosensores.so", IComunicacaoSensores.class);
		}
		
		
		return conector;
	}

}
