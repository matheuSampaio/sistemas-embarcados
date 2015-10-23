#include <Wire.h>
#include "ADXL345.h"

ADXL345 acel = ADXL345();

struct Eixos {
  int acelX, acelY, acelZ;
};

Eixos eixos;

void setup(){
  Serial.begin(9600);  
  acel.powerOn(); //inicializar 
}

void enviarEixos() { //envia o buffer com dos dados para Serial
int tam = sizeof(eixos); //tamanho da informaçao a ser enviada 

//Buffer 
char buff[tam]; // tamanho do buffer de dado
memcpy(&buff,&eixos,6); //copia todos os valores de eixo para o buffer 

//Enviado o Buffer I começa e F termina o Buffer
Serial.write('I'); //inicio
Serial.write((uint8_t*)&buff, tam); // cast endereço do tipo unsig 8 inteiro nao sinalizado
Serial.write('F'); //Final

}

void loop(){
acel.readAccel(&eixos.acelX, &eixos.acelY, &eixos.acelZ);//leitura dos valores dos eixos X, Y e Z do acelerometro
enviarEixos();
delay(100);
}


