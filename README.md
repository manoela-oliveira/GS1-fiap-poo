# Global Solution POO - Progamação Orientada a Objeto

> **Professor:** Ygor Moraes Martins dos Anjos
---

## Instruções para entrega - Estruturação do projeto

O objetivo macro é construir uma plataforma de monitoramento de sistemas espaciais. 
### 1. Classe Abstrata (`ComponenteEspacial`)
*   Define os atributos comuns: `id`, `nome`, `status` e `temperatura`.
*   Contém os métodos concretos `ligar()` e `desligar()`.
*   Contém ao menos 1 método abstrato a ser implementado pelas subclasses.

### 2. Interface (`Sensor`)
*   Define os métodos obrigatórios: `lerValor()`, `verificarFuncionamento()` e `retornarTipo()`.
*   Implementada em 3 classes distintas: `SensorTemperatura`, `SensorPressao` e `SensorRadiacao`.

### 3. Encapsulamento (`DadosMissao`)
*   Atributos estritamente privados.
*   Acesso a dados sensíveis (como coordenadas e códigos) protegido por validação de senha.
*   Getters e setters com validação de consistência (ex: não aceitar valores negativos ou inválidos).

### 4. Herança (`SistemaPropulsao`)
*   Classe abstrata base que define o comportamento padrão de propulsão.
*   Subclasses `PropulsaoQuimica` e `PropulsaoEletrica` herdam da base, implementando atributos próprios e sobrescrevendo o método `acelerar()`.
*   Uso correto da instrução `super()` para acessar construtores e métodos da classe mãe.

## Funcionalidades do Sistema

*   **Sistema de Sensores:** Leitura de valores (simulados por valores aleatórios), verificação de funcionamento e detecção de limites de segurança.
*   **Sistema de Propulsão:** Controle de ativação, aceleração com potência controlada (0-100), cálculo de empuxo e validação de valores de entrada.
*   **Dados da Missão:** Controle de coordenadas sob autenticação, gerenciamento do nível de combustível (com alerta automático caso fique abaixo de 20%), trajetória e número de tripulantes.
*   **Sistema de Monitoramento:** Menu interativo em modo texto via terminal (`SistemaMonitoramento.java`) para navegação pelas funcionalidades.
*   **Sistema de Alertas:** Monitoramento e exibição de mensagens no console com diferentes níveis de gravidade (`ATENÇÃO`, `ALERTA`, `CRÍTICO`).

---

### Tecnologias Utilizadas
<p>
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=git,java" />
  </a>
</p>

### Tópicos Avaliados
<ul>
  <li>Encapsulamento</li>
  <li>Herança</li>
  <li>Associação</li>
  <li>Construtores</li>
  <li>Polimorfismo</li>
  <li>Abstração</li>
  <li>Interfaces</li>
  <li>Clean Code</li>
  <li>Aplicação das instruções</li>
</ul>

## Estrutura final da entrega

```
  monitoramentooespacial/
  ├── main
  |   ├── SistemaMonitoramento.java
  │   └── TestesSistemaMonitoramento.java
  └── model
      ├── ComponenteEspacial.java
      ├── DadosMissao.java
      ├── PropulsaoEletrica.java
      ├── PropulsaoQuimica.java
      ├── Sensor.java
      ├── SensorPressao.java
      ├── SensorRadiacao.java
      ├── SensorTemperatura.java
      └── SistemaPropulsao.java
```
 
## Visualização do sistema no Astah
<img width="1240" height="820" alt="Image" src="https://github.com/user-attachments/assets/bcd5afad-814c-4f80-bcbf-02b5765a8ea6" />
