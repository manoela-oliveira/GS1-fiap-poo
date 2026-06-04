package br.com.monitoramentoespacial.model;

/* Esta interface serve como um contrato operacional para os sensores. Garante que independente do tipo 
   físico denvariável que um sensor esteja analisando, ele responderá às mesmas "solicitações". */

public interface Sensor {
    double lerValor();
    boolean verificarFuncionamento();
    String retornarTipo();
}