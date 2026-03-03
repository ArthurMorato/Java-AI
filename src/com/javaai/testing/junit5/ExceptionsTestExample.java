package com.javaai.testing.junit5;

/**
 * ExceptionsTestExample
 *
 * Objetivo:
 *  - Demonstrar testes de exceção com assertThrows no JUnit 5.
 */
public class ExceptionsTestExample {

    public static void main(String[] args) {
        System.out.println("=== JUnit 5 – assertThrows ===");
        System.out.println("Use assertThrows para garantir que regras de negócio disparem as exceções corretas.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5)               ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import static org.junit.jupiter.api.Assertions.assertThrows;
    //
    // class ExceptionsTestExampleTest {
    //
    //     static class LibraryService {
    //         void borrowBook(String memberId, String bookId) {
    //             if (memberId == null) {
    //                 throw new IllegalArgumentException("memberId é obrigatório");
    //             }
    //         }
    //     }
    //
    //     @Test
    //     void deveLancarExcecaoQuandoMemberIdNulo() {
    //         LibraryService service = new LibraryService();
    //         assertThrows(IllegalArgumentException.class,
    //                 () -> service.borrowBook(null, "book-1"));
    //     }
    // }
}

