package br.com.monitoramentoespacial.main;

import br.com.monitoramentoespacial.model.*;
import java.util.Scanner;

public class SistemaMonitoramento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        DadosMissao missao = new DadosMissao("45°12'S 130°20'W", "Órbita de Titã", 5, "SEGURANCA2026");
        SensorTemperatura temp = new SensorTemperatura("S-01", "Sensor Térmico", 60.0);
        SensorPressao pressao = new SensorPressao("S-02", "Sensor de Pressão", 2.5);
        SensorRadiacao rad = new SensorRadiacao("S-03", "Sensor de Radiação", 4.0);
        PropulsaoQuimica motorPrincipal = new PropulsaoQuimica("PROP-01", "Motor Delta", "Oxigênio Líquido", 300.0);
        PropulsaoEletrica motorManobra = new PropulsaoEletrica("PROP-02", "Motor Iônico Xenon", "Gás Xenon", 15.0);
        
        temp.ligar();
        pressao.ligar();
        rad.ligar();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=========================================");
            System.out.println("    SISTEMA DE MONITORAMENTO ESPACIAL");
            System.out.println("=========================================\n");
            System.out.println("===== PAINEL PRINCIPAL =====");
            System.out.println("1 - Verificar sensores");
            System.out.println("2 - Realizar diagnóstico dos componentes");
            System.out.println("3 - Gerenciar dados da missão");
            System.out.println("4 - Simular alteração (forçar alertas)");
            System.out.println("5 - Controlar propulsão");
            System.out.println("6 - Exibir relatório da missão");
            System.out.println("0 - Sair do sistema");
            System.out.print("\nEscolha uma opção: ");
            
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Erro! Opção inválida.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.printf("Temperatura: %.2f °C | Alerta: [%s]\n", temp.lerValor(), temp.obterClassificacaoAlerta());
                    System.out.printf("Pressão: %.2f atm | Alerta: [%s]\n", pressao.lerValor(), pressao.obterClassificacaoAlerta());
                    System.out.printf("Radiação: %.2f mSv | Alerta: [%s]\n", rad.lerValor(), rad.obterClassificacaoAlerta());
                    break;
                case 2:
                    temp.realizarAutoDiagnostico();
                    pressao.realizarAutoDiagnostico();
                    rad.realizarAutoDiagnostico();
                    motorPrincipal.realizarAutoDiagnostico();
                    motorManobra.realizarAutoDiagnostico();
                    break;
                case 3:
                    System.out.println("1 - Ver coordenadas");
                    System.out.println("2 - Alterar nível de combustível");
                    System.out.print("Escolha uma ação: ");
                    int acaoMissao = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (acaoMissao == 1) {
                        System.out.print("Digite a chave de segurança para ver as coordenadas: ");
                        String chave = scanner.nextLine();
                        System.out.println("Coordenadas: " + missao.getCoordenadas(chave));
                    } else if (acaoMissao == 2) {
                        System.out.print("Digite o novo percentual de combustível (0 a 100): ");
                        double comb = scanner.nextDouble();
                        missao.setNivelCombustivelPorcentagem(comb);
                    }
                    break;
                case 4:
                    // Caso surja o erro InputMismatchException ao tentar digitar decimais, é por conta do idioma do seu computador
                    System.out.println("1 - Temperatura");
                    System.out.println("2 - Pressão");
                    System.out.println("3 - Radiação");
                    System.out.print("\n(Se seu computador estiver com o idioma em PT-BR utilize vírgula para decimais, caso contrário, utilize . normalmente)");
                    System.out.print("Selecione o sensor: ");
                    int sensorSel = scanner.nextInt();
                    
                    System.out.print("Digite o novo valor: ");
                    double valForcado = scanner.nextDouble();
                    
                    if (sensorSel == 1) {
                        temp.forcarLeituraManual(valForcado);
                    } else if (sensorSel == 2) {
                        pressao.forcarLeituraManual(valForcado);
                    } else if (sensorSel == 3) {
                        rad.forcarLeituraManual(valForcado);
                    }
                    System.out.println("Valor de simulação injetado.");
                    break;
                case 5:
                    System.out.println("1 - Ligar Motores");
                    System.out.println("2 - Desligar Motores");
                    System.out.println("3 - Acelerar Motores");
                    System.out.print("\nEscolha uma ação: ");
                    int acaoProp = scanner.nextInt();
                    
                    if (acaoProp == 1) {
                        motorPrincipal.ligarMotor();
                        motorManobra.ligarMotor();
                    } else if (acaoProp == 2) {
                        motorPrincipal.desligarMotor();
                        motorManobra.desligarMotor();
                    } else if (acaoProp == 3) {
                        System.out.print("Digite a potência para o Motor Principal (0 a 100): ");
                        double potP = scanner.nextDouble();
                        motorPrincipal.acelerar(potP);
                        
                        System.out.print("Digite a potência para o Motor de Manobra (0 a 100): ");
                        double potM = scanner.nextDouble();
                        motorManobra.acelerar(potM);
                    }
                    break;
                case 6:
                    // Relatório Consolidado Exigido nos Requisitos
                    System.out.println("\n==================================================");
                    System.out.println("          RELATÓRIO GERAL DE STATUS DA MISSÃO     ");
                    System.out.println("==================================================");
                    System.out.println("Trajetória Registrada: " + missao.getTrajetoria());
                    System.out.println("Tripulantes a bordo: " + missao.getNumeroTripulantes());
                    System.out.println("Tanques de Combustível: " + missao.getNivelCombustivelPorcentagem() + "%");
                    System.out.println("--------------------------------------------------");
                    System.out.printf("Sistemas de Propulsão:\n");
                    System.out.printf("  - %s | Ativo: %b | Potência: %.1f%%\n", 
                        motorPrincipal.getNome(), motorPrincipal.isAtivo(), motorPrincipal.getPotenciaPorcentagem());
                    System.out.printf("  - %s | Ativo: %b | Potência: %.1f%%\n", 
                        motorManobra.getNome(), motorManobra.isAtivo(), motorManobra.getPotenciaPorcentagem());
                    System.out.println("--------------------------------------------------");
                    System.out.printf("Últimas Leituras de Sensores:\n");
                    System.out.printf("  - %s: %.2f °C [%s]\n", temp.getNome(), temp.lerValor(), temp.obterClassificacaoAlerta());
                    System.out.printf("  - %s: %.2f atm [%s]\n", pressao.getNome(), pressao.lerValor(), pressao.obterClassificacaoAlerta());
                    System.out.printf("  - %s: %.2f mSv [%s]\n", rad.getNome(), rad.lerValor(), rad.obterClassificacaoAlerta());
                    System.out.println("==================================================");
                    break;
                case 0:
                    System.out.println("Encerrando monitoramento...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}