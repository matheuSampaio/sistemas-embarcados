/*
 * Extern.cpp
 *
 *
 *  Created on: 15 de out de 2015
 *      Author: MATHEUS
 */

#include "Extern.h"
#include "Comunicacao.h"

struct Eixos {
  short acelX, acelY, acelZ;
};

Eixos eixos;

Comunicacao com = NULL;

int iniciar(char* porta){
	com = Comunicacao(porta);
	return com.iniciar();
}
int ler(){
	char ci, cf; //caracter inicio, caracter final

	//realiza a leitura do caracter i que representa o inicio
	int resultado = com.ler((char*)&ci, sizeof(ci)); //pq o arduino manda primeiro o caracter i
	if(resultado == EXIT_SUCCESS && (ci == 'I')){ //testa se conseguiu ler o byte
		//se a leitura ocorrer bem
		resultado = com.ler((char*)&eixos, sizeof(eixos)); // lê a estrutura
		if(resultado == EXIT_SUCCESS){
			//se a leitura ocorrer bem
			resultado = com.ler((char*)&cf, sizeof(cf)); //lê o caracter de finalização
			if(resultado == EXIT_SUCCESS && (cf == 'F')){
				resultado = EXIT_SUCCESS;
			}
		}
	}
	return resultado;
}
int getAcelX(){
	return eixos.acelX;
}
int getAcelY(){
	return eixos.acelY;
}
int getAcelZ(){
	return eixos.acelZ;
}
int finalizar(){
	return com.finalizar();
}



