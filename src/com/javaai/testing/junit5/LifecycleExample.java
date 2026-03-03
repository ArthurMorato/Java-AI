package com.javaai.testing.junit5;

/**
 * LifecycleExample
 *
 * Objetivo:
 *  - Explicar o ciclo de vida de testes no JUnit 5:
 *    @BeforeAll, @AfterAll, @BeforeEach, @AfterEach.
 *
 * Importante:
 *  - @BeforeAll / @AfterAll: configuram/limpam recursos caros (por classe).
 *  - @BeforeEach / @AfterEach: preparam/limpam o estado antes/depois de cada teste.
 */
public class LifecycleExample {

    public static void main(String[] args) {
        System.out.println("=== JUnit 5 – Lifecycle ===");
        System.out.println("@BeforeAll  → executa uma vez antes de TODOS os testes.");
        System.out.println("@BeforeEach → executa antes de CADA teste.");
        System.out.println("@AfterEach  → executa após CADA teste.");
        System.out.println("@AfterAll   → executa uma vez depois de TODOS os testes.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5)               ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.*;
    //
    // class LifecycleExampleTest {
    //
    //     @BeforeAll
    //     static void setupAll() {
    //         System.out.println("===> @BeforeAll: abrir conexão com banco de teste");
    //     }
    //
    //     @BeforeEach
    //     void setup() {
    //         System.out.println(" -> @BeforeEach: limpar dados da coleção de livros");
    //     }
    //
    //     @Test
    //     void testeUm() {
    //         System.out.println("    executando testeUm()");
    //     }
    //
    //     @Test
    //     void testeDois() {
    //         System.out.println("    executando testeDois()");
    //     }
    //
    //     @AfterEach
    //     void tearDown() {
    //         System.out.println(" <- @AfterEach: limpar contexto de teste");
    //     }
    //
    //     @AfterAll
    //     static void tearDownAll() {
    //         System.out.println("===> @AfterAll: fechar conexão com banco de teste");
    //     }
    // }
}

