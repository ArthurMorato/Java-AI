package com.javaai.testing.junit5;

/**
 * AssumptionsExample
 *
 * Objetivo:
 *  - Demonstrar o uso de assumptions no JUnit 5:
 *    assumeTrue, assumingThat.
 *
 * Uso típico:
 *  - Pular testes quando uma condição de ambiente não é atendida
 *    (ex.: variável de ambiente, SO, banco disponível).
 */
public class AssumptionsExample {

    public static void main(String[] args) {
        System.out.println("=== JUnit 5 – Assumptions ===");
        System.out.println("Use assumptions para PULAR testes quando o ambiente não é o esperado.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5)               ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import org.junit.jupiter.api.condition.EnabledOnOs;
    // import org.junit.jupiter.api.condition.OS;
    // import static org.junit.jupiter.api.Assumptions.*;
    //
    // class AssumptionsExampleTest {
    //
    //     @Test
    //     void deveRodarSomenteEmAmbienteDeDev() {
    //         String env = System.getenv("APP_ENV");
    //         assumeTrue("dev".equals(env), "Teste pulado: não está em ambiente de dev");
    //         // restante do teste...
    //     }
    //
    //     @Test
    //     @EnabledOnOs(OS.LINUX)
    //     void deveRodarSomenteNoLinux() {
    //         assumingThat(System.getProperty("os.name").contains("Linux"),
    //                 () -> System.out.println("Rodando cenário específico de Linux"));
    //     }
    // }
}

