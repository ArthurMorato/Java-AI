package com.javaai.testing.patterns;

/**
 * GivenWhenThenExample
 *
 * Objetivo:
 *  - Demonstrar o estilo BDD Given-When-Then em testes.
 *
 * Estilo:
 *  - Given: contexto / pré-condições.
 *  - When: ação principal.
 *  - Then: resultado esperado.
 */
public class GivenWhenThenExample {

    public static void main(String[] args) {
        System.out.println("=== Padrão de teste – Given / When / Then (BDD) ===");
        System.out.println("Given  → dado um contexto específico da biblioteca.");
        System.out.println("When   → quando uma ação acontece (ex.: empréstimo).");
        System.out.println("Then   → então esperamos determinado resultado.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5)               ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import static org.junit.jupiter.api.Assertions.*;
    //
    // class GivenWhenThenExampleTest {
    //
    //     static class Library {
    //         private int availableBooks = 10;
    //
    //         void borrowBook() {
    //             availableBooks--;
    //         }
    //
    //         int getAvailableBooks() {
    //             return availableBooks;
    //         }
    //     }
    //
    //     @Test
    //     void dadoLivroDisponivel_quandoEmpresta_entaoQuantidadeDiminui() {
    //         // Given
    //         Library library = new Library();
    //         int initial = library.getAvailableBooks();
    //
    //         // When
    //         library.borrowBook();
    //
    //         // Then
    //         assertEquals(initial - 1, library.getAvailableBooks());
    //     }
    // }
}

