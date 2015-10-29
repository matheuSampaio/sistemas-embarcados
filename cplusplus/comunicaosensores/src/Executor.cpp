/*
 * Executor.cpp
 *
 *  Created on: 15 de out de 2015
 *      Author: MATHEUS
 */

#include "Comunicacao.h"
#include <math.h>
#include <iostream>;
using namespace std;

struct Eixos {
  short acelX, acelY, acelZ;
};

int main(int argc, char **argv){

	//criar uma instância da classe de comunicacao
	Comunicacao com = Comunicacao("COM6");

	if(com.iniciar() == EXIT_SUCCESS){
		char ci, cf; //caracter inicio, caracter final
		Eixos eixos;

		//enquanto estiver executando
		while(true){
			//realiza a leitura do caracter i que representa o inicio
			int resultado = com.ler((char*)&ci, sizeof(ci)); //pq o arduino manda primeiro o caracter i
			if(resultado == EXIT_SUCCESS && (ci == 'I')){ //testa se conseguiu ler o byte
				//se a leitura ocorrer bem
				resultado = com.ler((char*)&eixos, sizeof(eixos)); // lê a estrutura
				if(resultado == EXIT_SUCCESS){
					//se a leitura ocorrer bem
					resultado = com.ler((char*)&cf, sizeof(cf)); //lê o caracter de finalização
					if(resultado == EXIT_SUCCESS && (cf == 'F')){
						cout << "x = " << eixos.acelX << ", y = " << eixos.acelY << ", z = " << eixos.acelZ << endl;
					}
				}
			}

			Sleep(1000);
		}
	}
	return EXIT_SUCCESS;
}



