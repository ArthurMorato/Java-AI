package com.javaai.testing.junit5;

/**
 * ParameterizedExample
 *
 * Objetivo:
 *  - Mostrar testes parametrizados com JUnit 5:
 *    @ParameterizedTest, @ValueSource, @CsvSource.
 */
public class ParameterizedExample {

    public static void main(String[] args) {
        System.out.println("=== JUnit 5 – Parameterized Tests ===");
        System.out.println("Use testes parametrizados para cobrir múltiplos cenários com menos código repetido.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5)               ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.params.ParameterizedTest;
    // import org.junit.jupiter.params.provider.CsvSource;
    // import org.junit.jupiter.params.provider.ValueSource;
    // import static org.junit.jupiter.api.Assertions.*;
    //
    // class ParameterizedExampleTest {
    //
    //     @ParameterizedTest
    //     @ValueSource(strings = { "Clean Code", "Effective Java", "DDD" })
    //     void tituloNaoDeveSerVazio(String title) {
    //         assertFalse(title.isBlank());
    //     }
    //
    //     @ParameterizedTest
    //     @CsvSource({
    //         "Clean Code, 95.0",
    //         "Effective Java, 120.0",
    //         "DDD, 150.0"
    //     })
    //     void precoDeveSerPositivo(String title, double price) {
    //         assertTrue(price > 0, () -> "Preço deve ser positivo para " + title);
    //     }
    // }
}

