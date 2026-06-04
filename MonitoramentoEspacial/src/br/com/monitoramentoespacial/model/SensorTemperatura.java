package br.com.monitoramentoespacial.model;

public class SensorTemperatura extends ComponenteEspacial implements Sensor {
    private double limiteAlertaCelsius;
    private double valorAtualCelsius;
    // Atributo adicionado exclusivamente para execução da opção 4 (forçar alertas) no main
    private boolean isForcado = false;

    public SensorTemperatura(String id, String nome, double limiteAlertaCelsius) {
        super(id, nome);
        this.setLimiteAlertaCelsius(limiteAlertaCelsius);
        this.valorAtualCelsius = 22.0;
    }

     @Override
    public double lerValor() {
        if (!isAtivo()) {
            System.out.println("Aviso: Sensor de temperatura [" + getNome() + "] está desativado.");
            return 0.0;
        }
        
        // Só gera valor aleatório se NÃO for forçada outro valor no painel
        if (!this.isForcado) {
            this.valorAtualCelsius = 15.0 + (Math.random() * 40.0);
            this.setTemperaturaCelsius(this.valorAtualCelsius);
        }
        
        return this.valorAtualCelsius;
    }

    public void forcarLeituraManual(double valor) {
        this.valorAtualCelsius = valor;
        this.setTemperaturaCelsius(valor);
        this.isForcado = true;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isAtivo() && getTemperaturaCelsius() < 120.0;
    }

    @Override
    public String retornarTipo() {
        return "Temperatura";
    }

    public String obterClassificacaoAlerta() {
        if (this.valorAtualCelsius <= this.limiteAlertaCelsius) {
            return "NORMAL";
        } else if (this.valorAtualCelsius <= this.limiteAlertaCelsius * 1.15) {
            return "ATENÇÃO";
        } else if (this.valorAtualCelsius <= this.limiteAlertaCelsius * 1.40) {
            return "ALERTA";
        } else {
            return "CRÍTICO";
        }
    }

    @Override
    public void realizarAutoDiagnostico() {
        System.out.println("\nAuto-Diagnóstico - Sensor de Temperatura");
        System.out.println("ID: " + getId() + " | Nome: " + getNome() + " | Ativo: " + isAtivo());
        System.out.println("Valor de Leitura Atual: " + this.valorAtualCelsius + " °C");
        System.out.println("Limite de Alerta Térmico: " + this.limiteAlertaCelsius + " °C");
        
        String alerta = obterClassificacaoAlerta();
        System.out.println("Nível do Alerta Térmico: [" + alerta + "]");
    }

    public double getLimiteAlertaCelsius() {
        return limiteAlertaCelsius;
    }

    private void setLimiteAlertaCelsius(double limite) {
        if (limite > -273.15) {
            this.limiteAlertaCelsius = limite;
        } else {
            System.out.println("Erro! O limite térmico não pode ser inferior ao zero absoluto.");
        }
    }

}