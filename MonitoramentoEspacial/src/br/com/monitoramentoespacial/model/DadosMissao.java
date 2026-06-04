package br.com.monitoramentoespacial.model;

/* Classe com dados sensíveis */

public class DadosMissao {
    private String coordenadas;
    private double nivelCombustivelPorcentagem;
    private String trajetoria;
    private int numeroTripulantes;
    private String chaveAcessoSeguro;

    public DadosMissao(String coordenadasIniciais, String trajetoria, int numeroTripulantes, String chaveSeguranca) {
        this.setChaveAcessoSeguro(chaveSeguranca);
        this.coordenadas = coordenadasIniciais;
        this.setTrajetoria(trajetoria);
        this.setNumeroTripulantes(numeroTripulantes);
        this.setNivelCombustivelPorcentagem(100.0);
    }

    public String getCoordenadas(String chave) {
        if (validarChave(chave)) {
            return this.coordenadas;
        }
        System.out.println("ERRO DE SEGURANÇA: Chave de acesso incorreta. Dados confidenciais retidos.");
        return "ACESSO_BLOQUEADO";
    }

    public void setCoordenadas(String coordenadas, String chave) {
        if (validarChave(chave)) {
            if (coordenadas != null && !coordenadas.trim().isEmpty()) {
                this.coordenadas = coordenadas;
                System.out.println("Sucesso: Coordenadas espaciais de trajeto alteradas.");
            } else {
                System.out.println("Erro: Nova coordenada inválida.");
            }
        } else {
            System.out.println("ERRO DE SEGURANÇA: Alteração de coordenadas bloqueada.");
        }
    }

    public double getNivelCombustivelPorcentagem() {
        return nivelCombustivelPorcentagem;
    }

    public void setNivelCombustivelPorcentagem(double combustivel) {
        if (combustivel >= 0.0 && combustivel <= 100.0) {
            this.nivelCombustivelPorcentagem = combustivel;
            this.verificarAlertaCombustivel();
        } else {
            System.out.println("Erro! Insira um valor percentual válido de combustível (0% a 100%).");
        }
    }

    private void verificarAlertaCombustivel() {
        if (this.nivelCombustivelPorcentagem < 20.0) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("[ALERTA DE COMBUSTÍVEL CRÍTICO] - Reserva operacional!");
            System.out.println("Quantidade restante no sistema de tanques: " + this.nivelCombustivelPorcentagem + "%");
            System.out.println("--------------------------------------------------");
        }
    }

    public String getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(String trajetoria) {
        if (trajetoria != null && !trajetoria.trim().isEmpty()) {
            this.trajetoria = trajetoria;
        } else {
            System.out.println("Erro! Caminho da trajetória espacial inválido.");
        }
    }

    public int getNumeroTripulantes() {
        return numeroTripulantes;
    }

    public void setNumeroTripulantes(int quantidade) {
        if (quantidade >= 0) {
            this.numeroTripulantes = quantidade;
        } else {
            System.out.println("Erro! Número de tripulantes a bordo não pode ser negativo.");
        }
    }

    private void setChaveAcessoSeguro(String chave) {
        if (chave != null && !chave.trim().isEmpty()) {
            this.chaveAcessoSeguro = chave;
        } else {
            this.chaveAcessoSeguro = "MOTIVA2026";
        }
    }

    private boolean validarChave(String chaveDigitada) {
        return this.chaveAcessoSeguro.equals(chaveDigitada);
    }
}