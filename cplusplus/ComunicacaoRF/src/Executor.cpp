/*
 * Executor.cpp
 *
 *  Created on: 26 de nov de 2015
 *      Author: MATHEUS
 */

#include <iostream>
#include "Comunicacao.h"
using namespace std;

struct InfoRF {
	short id;
	short luminosidade;
	short temperatura;
	short distancia;
};

int main(int argc, char **argv) {
	InfoRF infoRF = { 0 };

	Comunicacao com("COM6");
	if (com.iniciar() == EXIT_SUCCESS) { //testa para erro - nega a condicao
		char ci, cf;

		while (true) {
			int resultado = com.ler((char*) &ci, sizeof(ci));
			if ((resultado == EXIT_SUCCESS) && (ci == 'I')) {
				resultado = com.ler((char*) &infoRF, sizeof(infoRF));
				if (resultado == EXIT_SUCCESS) {
					resultado = com.ler((char*) &cf, sizeof(cf));

					if ((resultado == EXIT_SUCCESS) && (cf == 'F')) {
						cout << "id: " << infoRF.id << ", luminosidade: " << infoRF.luminosidade
							 << ", temperatura: " << infoRF.temperatura
							 << ", distancia: " << infoRF.distancia << endl;
					}
				}

			}
			Sleep(50);
		}
	}
	return EXIT_SUCCESS;
}

