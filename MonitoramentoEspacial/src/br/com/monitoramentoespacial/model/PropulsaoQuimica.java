package br.com.monitoramentoespacial.model;

public class PropulsaoQuimica extends SistemaPropulsao {
    private String elementoOxidante;
    private double pressaoCamaraPsi;

    public PropulsaoQuimica(String id, String nome, String elementoOxidante, double pressaoCamaraPsi) {
        super(id, nome);
        this.elementoOxidante = elementoOxidante;
        this.setPressaoCamaraPsi(pressaoCamaraPsi);
    }

    @Override
    public void acelerar(double porcentagem) {
        if (!isAtivo()) {
            System.out.println("Ação Negada: Ligue o motor químico antes de tentar acelerá-lo.");
            return;
        }

        if (porcentagem < 0.0 || porcentagem > 100.0) {
            System.out.println("Erro! Potência solicitada de " + porcentagem + "% fora dos limites operacionais (0-100%).");
            return;
        }

        this.setPotenciaPorcentagem(porcentagem);
        // Motores térmicos sofrem drástica elevação de calor dependendo da queima
        double temperaturaMotor = 20.0 + (porcentagem * 6.8);
        this.setTemperaturaCelsius(temperaturaMotor);

        System.out.println("Acelerando Motor Químico [" + getNome() + "] para " + porcentagem + "%.");
        System.out.println("Status Térmico Atual: " + getTemperaturaCelsius() + " °C");
    }

    @Override
    public double calcularEmpuxo() {
        // Cálculo simulado de propulsores de queima (Quiloforça: kN)
        return getPotenciaPorcentagem() * (pressaoCamaraPsi / 110.0) * 2.2;
    }

    @Override
    public void realizarAutoDiagnostico() {
        System.out.println("\nAuto-Diagnóstico - Propulsor Químico");
        System.out.println("ID: " + getId() + " | Nome: " + getNome() + " | Status de Funcionamento: " + isAtivo());
        System.out.println("Potência de Trabalho: " + getPotenciaPorcentagem() + "% | Temperatura Interna: " + getTemperaturaCelsius() + " °C");
        System.out.println("Agente Oxidante: " + elementoOxidante + " | Pressão Limite Interna: " + pressaoCamaraPsi + " PSI");
    }

    public double getPressaoCamaraPsi() {
        return pressaoCamaraPsi;
    }

    private void setPressaoCamaraPsi(double pressao) {
        if (pressao >= 0.0) {
            this.pressaoCamaraPsi = pressao;
        } else {
            System.out.println("Erro! Pressão operacional do bocal não pode ser negativa.");
        }
    }
}