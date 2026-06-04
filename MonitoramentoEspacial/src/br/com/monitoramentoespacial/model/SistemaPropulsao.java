package br.com.monitoramentoespacial.model;

/*Esta classe é abstrata porque se trata de um termo genérico que não deve existir
  sozinho. O Sistema de Propulsão de uma nave precisa ser identificado por seus componentes.
  Ela unifica dados técnicos básicos de ativação de motores e limites físicos de aceleração.*/

public abstract class SistemaPropulsao extends ComponenteEspacial {
    private double potenciaPorcentagem;

    public SistemaPropulsao(String id, String nome) {
        super(id, nome);
        this.potenciaPorcentagem = 0.0; // Inicia estável e estático (desativado)
    }

    /* Método abstrato para calcular o empuxo físico, deixando os modelos de cálculo e unidades 
       específicas para as subclasses herdeiras. */
    public abstract double calcularEmpuxo();

    /* Assinatura de aceleração a ser reescrita com base no tipo físico de propulsão. */
    public abstract void acelerar(double porcentagem);

    public double getPotenciaPorcentagem() {
        return potenciaPorcentagem;
    }

    protected void setPotenciaPorcentagem(double potencia) {
        if (potencia >= 0.0 && potencia <= 100.0) {
            this.potenciaPorcentagem = potencia;
        } else {
            System.out.println("Erro! A potência do sistema de propulsão precisa situar-se entre 0% e 100%.");
        }
    }

    public void ligarMotor() {
        super.ligar();
    }

    public void desligarMotor() {
        this.setPotenciaPorcentagem(0.0); // Interrompe aceleração residual por segurança
        super.desligar();
    }
}