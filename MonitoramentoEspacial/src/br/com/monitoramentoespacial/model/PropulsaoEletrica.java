package br.com.monitoramentoespacial.model;

public class PropulsaoEletrica extends SistemaPropulsao {
    private String elementoGasoso;
    private double tensaoMegavolts;

    public PropulsaoEletrica(String id, String nome, String elementoGasoso, double tensaoMegavolts) {
        super(id, nome);
        this.elementoGasoso = elementoGasoso;
        this.setTensaoMegavolts(tensaoMegavolts);
    }

    @Override
    public void acelerar(double porcentagem) {
        if (!isAtivo()) {
            System.out.println("Ação Negada: Ative as bobinas do motor iônico antes de acelerar.");
            return;
        }

        if (porcentagem < 0.0 || porcentagem > 100.0) {
            System.out.println("Erro! Potência iônica solicitada de " + porcentagem + "% fora dos parâmetros.");
            return;
        }

        this.setPotenciaPorcentagem(porcentagem);
        // Propulsão elétrica gera níveis térmicos operacionais baixos e controlados
        double temperaturaIonica = 20.0 + (porcentagem * 0.45);
        this.setTemperaturaCelsius(temperaturaIonica);

        System.out.println("Ajustando potência do feixe iônico [" + getNome() + "] para " + porcentagem + "%.");
    }

    @Override
    public double calcularEmpuxo() {
        // Cálculo simulado de empuxo iônico (mN)
        return getPotenciaPorcentagem() * tensaoMegavolts * 1.35;
    }

    @Override
    public void realizarAutoDiagnostico() {
        System.out.println("\nAuto-Diagnóstico - Propulsor Iônico Elétrico");
        System.out.println("ID: " + getId() + " | Nome: " + getNome() + " | Ativo: " + isAtivo());
        System.out.println("Potência Adjusted: " + getPotenciaPorcentagem() + "% | Temperatura do Campo: " + getTemperaturaCelsius() + " °C");
        System.out.println("Elemento de Carga Estática: " + elementoGasoso + " | Potencial Elétrico: " + tensaoMegavolts + " MV");
    }

    public double getTensaoMegavolts() {
        return tensaoMegavolts;
    }

    private void setTensaoMegavolts(double valor) {
        if (valor >= 0.0) {
            this.tensaoMegavolts = valor;
        } else {
            System.out.println("Erro! Tensão elétrica não pode ser inferior a 0.");
        }
    }
}