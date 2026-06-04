package br.com.monitoramentoespacial.model;

public class SensorRadiacao extends ComponenteEspacial implements Sensor {
    private double limiteAlertaSievert;
    private double valorAtualSievert;
    // Atributo adicionado exclusivamente para execução da opção 4 (forçar alertas) no main
    private boolean isForcado = false;

    public SensorRadiacao(String id, String nome, double limiteAlertaSievert) {
        super(id, nome);
        this.setLimiteAlertaSievert(limiteAlertaSievert);
        this.valorAtualSievert = 0.15;
    }

    @Override
    public double lerValor() {
        if (!isAtivo()) {
            System.out.println("Aviso: Sensor de radiação [" + getNome() + "] está inativo.");
            return 0.0;
        }
        
        // Só gera valor aleatório se NÃO for forçada outro valor no painel
        if (!this.isForcado) {
            this.valorAtualSievert = Math.random() * 5.0;
        }
        
        return this.valorAtualSievert;
    }

    public void forcarLeituraManual(double valor) {
        this.valorAtualSievert = valor;
        this.isForcado = true;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isAtivo();
    }

    @Override
    public String retornarTipo() {
        return "Radiação";
    }

    public String obterClassificacaoAlerta() {
        if (this.valorAtualSievert <= this.limiteAlertaSievert) {
            return "NORMAL";
        } else if (this.valorAtualSievert <= this.limiteAlertaSievert * 1.15) {
            return "ATENÇÃO";
        } else if (this.valorAtualSievert <= this.limiteAlertaSievert * 1.40) {
            return "ALERTA";
        } else {
            return "CRÍTICO";
        }
    }

    @Override
    public void realizarAutoDiagnostico() {
        System.out.println("\nAuto-Diagnóstico - Sensor de Radiação");
        System.out.println("ID: " + getId() + " | Nome: " + getNome() + " | Ativo: " + isAtivo());
        System.out.println("Nível Radiação Atual: " + this.valorAtualSievert + " mSv");
        System.out.println("Limite de Exposição Máxima: " + this.limiteAlertaSievert + " mSv");
        
        String alerta = obterClassificacaoAlerta();
        System.out.println("Nível do Alerta de Radiação: [" + alerta + "]");
    }

    public double getLimiteAlertaSievert() {
        return limiteAlertaSievert;
    }

    private void setLimiteAlertaSievert(double limite) {
        if (limite >= 0.0) {
            this.limiteAlertaSievert = limite;
        } else {
            System.out.println("Erro! Limite de radiação de segurança precisa ser um valor positivo.");
        }
    }
}