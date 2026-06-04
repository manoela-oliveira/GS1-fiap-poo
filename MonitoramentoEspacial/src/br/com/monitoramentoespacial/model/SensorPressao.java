package br.com.monitoramentoespacial.model;

public class SensorPressao extends ComponenteEspacial implements Sensor {
    private double limiteAlertaAtm;
    private double valorAtualAtm;
    // Atributo adicionado exclusivamente para execução da opção 4 (forçar alertas) no main
    private boolean isForcado = false;

    public SensorPressao(String id, String nome, double limiteAlertaAtm) {
        super(id, nome);
        this.setLimiteAlertaAtm(limiteAlertaAtm);
        this.valorAtualAtm = 1.0;
    }

    @Override
    public double lerValor() {
        if (!isAtivo()) {
            System.out.println("Aviso: Sensor de pressão [" + getNome() + "] está desativado.");
            return 0.0;
        }
        
        // Só gera valor aleatório se NÃO for forçada outro valor no painel
        if (!this.isForcado) {
            this.valorAtualAtm = 0.4 + (Math.random() * 2.2);
        }
        
        return this.valorAtualAtm;
    }

    public void forcarLeituraManual(double valor) {
        this.valorAtualAtm = valor;
        this.isForcado = true;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isAtivo();
    }

    @Override
    public String retornarTipo() {
        return "Pressão";
    }

    public String obterClassificacaoAlerta() {
        if (this.valorAtualAtm <= this.limiteAlertaAtm) {
            return "NORMAL";
        } else if (this.valorAtualAtm <= this.limiteAlertaAtm * 1.15) {
            return "ATENÇÃO";
        } else if (this.valorAtualAtm <= this.limiteAlertaAtm * 1.40) {
            return "ALERTA";
        } else {
            return "CRÍTICO";
        }
    }

    @Override
    public void realizarAutoDiagnostico() {
        System.out.println("\nAuto-Diagnóstico - Sensor de Pressão");
        System.out.println("ID: " + getId() + " | Nome: " + getNome() + " | Ativo: " + isAtivo());
        System.out.println("Pressão Atual Registrada: " + this.valorAtualAtm + " atm");
        System.out.println("Limite Operacional Seguro: " + this.limiteAlertaAtm + " atm");
        
        String alerta = obterClassificacaoAlerta();
        System.out.println("Nível do Alerta de Pressão: [" + alerta + "]");
    }

    public double getLimiteAlertaAtm() {
        return limiteAlertaAtm;
    }

    private void setLimiteAlertaAtm(double limite) {
        if (limite >= 0.0) {
            this.limiteAlertaAtm = limite;
        } else {
            System.out.println("Erro! Pressão limite de alerta não pode ser negativa.");
        }
    }
}