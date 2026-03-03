package com.javaai.testing.patterns;

/**
 * AAAPatternExample
 *
 * Objetivo:
 *  - Demonstrar o padrão Arrange-Act-Assert (AAA) em testes.
 *
 * Estrutura:
 *  - Arrange: preparar o cenário.
 *  - Act: executar a ação a ser testada.
 *  - Assert: verificar o resultado.
 */
public class AAAPatternExample {

    public static void main(String[] args) {
        System.out.println("=== Padrão de teste – Arrange, Act, Assert (AAA) ===");
        System.out.println("1) Arrange: preparar dados e dependências.");
        System.out.println("2) Act: executar o comportamento alvo.");
        System.out.println("3) Assert: verificar o resultado com asserções claras.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5)               ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import static org.junit.jupiter.api.Assertions.*;
    //
    // class AAAPatternExampleTest {
    //
    //     static class PriceCalculator {
    //         double total(double unitPrice, int quantity) {
    //             return unitPrice * quantity;
    //         }
    //     }
    //
    //     @Test
    //     void deveCalcularTotalSeguindoAAA() {
    //         // Arrange
    //         PriceCalculator calculator = new PriceCalculator();
    //         double unitPrice = 50.0;
    //         int quantity = 3;
    //
    //         // Act
    //         double total = calculator.total(unitPrice, quantity);
    //
    //         // Assert
    //         assertEquals(150.0, total);
    //     }
    // }
}

