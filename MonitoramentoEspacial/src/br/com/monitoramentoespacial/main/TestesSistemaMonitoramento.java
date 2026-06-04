package br.com.monitoramentoespacial.main;

import br.com.monitoramentoespacial.model.*;

public class TestesSistemaMonitoramento {

    public static void main(String[] args) {
        System.out.println("\n======= INICIANDO TESTES DO SISTEMA =======");

        // Nascimento dos objetos com dados inválidos
        System.out.println("\n>>> [TESTE 1] Validando eficiência do sistema frente a dados inválidos");
        
        System.out.println("\n--- Testando criação de missão com tripulantes negativos ---");
        DadosMissao missaoInvalida = new DadosMissao("00°00'N 00°00'W", "Órbita Neutra", -5, "TESTE123");
        System.out.println("Tripulantes atribuídos após validação interna: " + missaoInvalida.getNumeroTripulantes());

        System.out.println("\n--- Testando limites inválidos de combustível ---");
        // Tentativa de definir combustível acima de 100% ou negativo
        missaoInvalida.setNivelCombustivelPorcentagem(110.0);
        missaoInvalida.setNivelCombustivelPorcentagem(-5.0);
        System.out.println("Combustível em memória após tentativas: " + missaoInvalida.getNivelCombustivelPorcentagem() + "%");


        // Validando segurança de acesso aos dados sensíveis
        System.out.println("\n>>> [TESTE 2] Validando proteção de dados e chaves de segurança");
        DadosMissao missaoSegura = new DadosMissao("45°12'S 130°20'W", "Órbita Síncrona de Titã", 5, "SEGURANCA2026");

        System.out.println("\nTentativa de acesso com senha incorreta:");
        String retornoIncorreto = missaoSegura.getCoordenadas("SENHA_ERRADA");
        System.out.println("Retorno do sistema: " + retornoIncorreto);

        System.out.println("\nTentativa de modificação com senha incorreta:");
        missaoSegura.setCoordenadas("10°10'N 20°20'E", "CHAVE_FALSA");


        // Verificação de propagação de níveis de alerta automáticos nos sensores
        System.out.println("\n>>> [TESTE 3] Validação de níveis de alerta (NORMAL, ATENÇÃO, ALERTA, CRÍTICO)");
        SensorTemperatura sensorTemp = new SensorTemperatura("SENS-TEMP-01", "Radiador Leste", 50.0);
        sensorTemp.ligar();

        System.out.println("\n--- Sensor operando em nível NORMAL (Leitura <= Limite) ---");
        sensorTemp.forcarLeituraManual(45.0);
        sensorTemp.realizarAutoDiagnostico();

        System.out.println("\n--- Sensor operando em nível ATENÇÃO (Apenas acima do limite) ---");
        sensorTemp.forcarLeituraManual(55.0);
        sensorTemp.realizarAutoDiagnostico();

        System.out.println("\n--- Sensor operando em nível ALERTA (Entre 15% e 40% acima) ---");
        sensorTemp.forcarLeituraManual(65.0);
        sensorTemp.realizarAutoDiagnostico();

        System.out.println("\n--- Sensor operando em nível CRÍTICO (Acima de 40%) ---");
        sensorTemp.forcarLeituraManual(75.0);
        sensorTemp.realizarAutoDiagnostico();


        // Proteção e limites do sistema de propulsão
        System.out.println("\n>>> [TESTE 4] Validando barramentos físicos de potência e segurança");
        PropulsaoQuimica motorPrincipal = new PropulsaoQuimica("PROP-01", "Combustão Delta", "Oxigênio Líquido", 300.0);

        System.out.println("\n--- Tentando acelerar motor inativo ---");
        motorPrincipal.acelerar(50.0);

        System.out.println("\n--- Ativando motor e testando limites de potência inválidos (Fora de 0-100%) ---");
        motorPrincipal.ligarMotor();
        motorPrincipal.acelerar(105.0);
        motorPrincipal.acelerar(-10.0);

        System.out.println("\n>>> [TESTE 5] Verificação do acionamento de alerta automático de recursos");
        System.out.println("Definindo nível de combustível para nível crítico (15%):");
        missaoSegura.setNivelCombustivelPorcentagem(15.0);

        // Proteção do Java contra instanciamento de classes abstratas
        System.out.println("\n>>> [TESTE 6] Verificando isolamento físico de conceitos abstratos");
        System.out.println("Linhas propositalmente comentadas para evitar erros de compilação do compilador Java.\n Para realizar o teste, descomente-as.");
        
        // Descomente as linhas abaixo para validar o bloqueio físico do compilador:
        // ComponenteEspacial componenteGenerico = new ComponenteEspacial("C-GEN", "Hardware Fantasma");
        // SistemaPropulsao propulsaoGenerica = new SistemaPropulsao("P-GEN", "Motor Conceitual");
    }
}