package com.javaai.testing.junit5;

/**
 * BasicAssertionsExample
 *
 * Objetivo:
 *  - Introduzir asserções básicas do JUnit 5:
 *    assertEquals, assertNotEquals, assertTrue, assertFalse, assertNull, assertNotNull.
 *
 * Observação:
 *  - Esta classe é um programa standalone apenas explicativo.
 *  - Os exemplos de testes reais aparecem no bloco de comentário ao final.
 */
public class BasicAssertionsExample {

    public static void main(String[] args) {
        System.out.println("=== JUnit 5 – Basic Assertions ===");
        System.out.println("Use asserções para EXPRESSAR expectativas de forma clara.");
        System.out.println("- assertEquals: valores esperados vs atuais");
        System.out.println("- assertTrue / assertFalse: condições booleanas");
        System.out.println("- assertNull / assertNotNull: presença/ausência de valor");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5)               ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import static org.junit.jupiter.api.Assertions.*;
    //
    // class BasicAssertionsExampleTest {
    //
    //     @Test
    //     void deveValidarIgualdade() {
    //         int expected = 4;
    //         int actual = 2 + 2;
    //         assertEquals(expected, actual);
    //     }
    //
    //     @Test
    //     void deveValidarCondicaoBooleana() {
    //         boolean open = true;
    //         assertTrue(open);
    //         assertFalse(!open);
    //     }
    //
    //     @Test
    //     void deveValidarNullENotNull() {
    //         String name = null;
    //         assertNull(name);
    //         name = "Biblioteca Central";
    //         assertNotNull(name);
    //     }
    // }
}

