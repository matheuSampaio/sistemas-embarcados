#include <Wire.h>
#include "RCSwitch.h"

// instancias do emissor & receptor
RCSwitch emissor  = RCSwitch();
RCSwitch receptor = RCSwitch();

#define RFID_LIMITE_INFERIOR 19
#define RFID_LIMITE_SUPERIOR 32

#define DESLOCAMENTO_RFID  24
#define DESLOCAMENTO_LUMINOSIDADE 16
#define DESLOCAMENTO_TEMPERATURA 8

#define PINO_LDR    5
#define PINO_TEMPERATURA   2
#define PINO_DISTANCIA   3    

struct InfoRF {
  int id;
  int luminosidade;
  int temperatura;
  int distancia;
} infoRF;

void setup() {
  Serial.begin(9600);
  
  // configuracao do emissor/receptor RF
  emissor.enableTransmit(4);
  receptor.enableReceive(0);
}

long lerSensoresRF() {
  //Serial.println("lendo sensores...");
  
  long luminosidade  = analogRead(PINO_LDR);
  long temperatura = analogRead(PINO_TEMPERATURA);
  long distancia  = analogRead(PINO_DISTANCIA);

  luminosidade = map(luminosidade, 0, 1023, 0, 200);
  distancia = map(distancia, 0, 1023, 0, 250);
  temperatura = map(temperatura, 0, 1023, 0, 50);
    
  long rf = RFID_LIMITE_INFERIOR;
  long info = rf << DESLOCAMENTO_RFID;
  info = info | (luminosidade << DESLOCAMENTO_LUMINOSIDADE);
  info = info | (temperatura << DESLOCAMENTO_TEMPERATURA);
  info = info | distancia;
  
  return info;  
}

int RFIDValido(long info) {
  int valido = 0;
  
  infoRF.id = info >> DESLOCAMENTO_RFID;
  if ((infoRF.id >= RFID_LIMITE_INFERIOR) && (infoRF.id <= RFID_LIMITE_SUPERIOR)) {
     valido = 1; 
  }

  return valido;  
}

void emitir(long info) {
  //Serial.println("emitindo dados...");
  emissor.send(info, 32);  
}

long receber() {
  long info = -1;
  
  if (receptor.available()) {
    info = receptor.getReceivedValue();
    
    receptor.resetAvailable();    
  }
  
  return info;
}

void enviarParaUSB(long info){

  //TESTE
  //Serial.print("id: ");
  //Serial.print(infoRF.id);
  //Serial.print(", luminosidade: ");
  //Serial.print(infoRF.luminosidade); 
  //Serial.print(", temperatura: ");
  //Serial.print(infoRF.temperatura);
  //Serial.print(", distância: ");
  //Serial.println(infoRF.distancia);
  
  //char buff[sizeof(InfoRF)]={0};
  //memcpy(&buff, &infoRF, sizeof(InfoRF));
  //Serial.write('I');
  //Serial.write((uint8_t*) &buff, sizeof(InfoRF));
  //Serial.write('F');
  Serial.write(info);
  Serial.println(info);
}

int extrairDistancia(long info) {
  int distancia = (info & 255);
  return distancia;
}

int extrairTemperatura(long info) {
  int temperatura = (info & 65535) >> DESLOCAMENTO_TEMPERATURA;
  return temperatura;  
}

int extrairLuminosidade(long info) {
  int luminosidade = (info & 16777215) >> DESLOCAMENTO_LUMINOSIDADE;
  return luminosidade;
}


void loop() {
  // EMISSAO DE DADOS
  long info = lerSensoresRF(); 
  emitir(info);
    
  delay(50);
  
  // RECEPCAO DE DADOS
  info = receber();
  if (info != -1) {
    if (RFIDValido(info)) {
     //infoRF.luminosidade = extrairLuminosidade(info);
     //infoRF.temperatura = extrairTemperatura(info);
     //infoRF.distancia = extrairDistancia(info);
     enviarParaUSB(info);
     }
  }
}


