package br.com.monitoramentoespacial.model;

/* Esta classe abstrata serve como molde estrutural de hardware para qualquer componente físico do sistema 
   de monitoramento. Ela define estados operacionais comuns e ciclo de vida básico como ligar/desligar. */

public abstract class ComponenteEspacial {
    private String id;
    private String nome;
    private boolean ativo;
    private double temperaturaCelsius;

    public ComponenteEspacial(String id, String nome) {
        this.setId(id);
        this.setNome(nome);
        this.ativo = false;
        this.temperaturaCelsius = 20.0;
    }

    /* Método abstrato que obriga todos os componentes derivados a emitirem relatórios específicos sobre 
       seus estados de integridade. */
    public abstract void realizarAutoDiagnostico();

    public void ligar() {
        this.ativo = true;
        System.out.println("Componente [" + nome + "] (" + id + ") foi ativado com sucesso.");
    }

    public void desligar() {
        this.ativo = false;
        System.out.println("Componente [" + nome + "] (" + id + ") foi desligado.");
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
        } else {
            System.out.println("Erro! O ID do componente espacial não pode ser nulo ou vazio.");
        }
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Erro! O componente deve possuir um nome válido para identificação.");
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    protected void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public double getTemperaturaCelsius() {
        return temperaturaCelsius;
    }

    protected void setTemperaturaCelsius(double temperatura) {
        this.temperaturaCelsius = temperatura;
    }
}