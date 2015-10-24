/*
 * Comunicacao.h
 *
 *Class cujo os metodos possibilitam a comunicaÃ§Ã£o com o dispositivo
 *controlador que realiza a leitura de sensores de 10 eixos
 *
 *  Created on: 08/10/2015
 *      Author: Igo Romero costa de Souza
 */

#ifndef COMUNICACAO_H_
#define COMUNICACAO_H_

//Se for Windows inserir a biblioteca

#include <stdlib.h>
#if _WIN32 || _WIN64
#include <stddef.h>
#include <windows.h>
#endif

#ifdef __linux__
#include <unistd.h>
#define Sleep(x) usleep(x * 1000); //Sleep do Windows e linux  --- conversão de nano para micro
#endif

class Comunicacao {
private:
	char* porta;

// diretivas de compilação para ver que SO esta usando
// Se meu SO for linux isso entra no binario
#ifdef __linux__
	int hPorta;
#endif

//Se meu SO for windows isso entra no binario


#if _WIN32 || _WIN64
	HANDLE hPorta;
#endif


//métodos da classe
public:
	Comunicacao(char* porta);

	//iniciar a comunicacao com a porta Serial -- retorna 0 se der tudo certo, retorna #! se ocorrer problema
	int iniciar();

	//realizar a leitura de um buffer a partir de uma porta Serial
	int ler(char* buffer, long unsigned int bytesParaLer);

	//finaliza o uso da porta Serial
	int finalizar();

};

#endif /* COMUNICACAO_H_ */
