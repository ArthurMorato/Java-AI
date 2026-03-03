package com.javaai.testing.mockito;

/**
 * BasicMockExample
 *
 * Objetivo:
 *  - Mostrar o fluxo básico de uso do Mockito:
 *    mock(), when(...).thenReturn(...), verify().
 *
 * Observação:
 *  - Aqui NÃO adicionamos a dependência real do Mockito.
 *  - O código com Mockito está apenas nos comentários de teste.
 */
public class BasicMockExample {

    public static void main(String[] args) {
        System.out.println("=== Mockito – Básico (mock, when, verify) ===");
        System.out.println("1) mock() cria um dublê de teste de uma interface/classe.");
        System.out.println("2) when(...).thenReturn(...) define o comportamento esperado.");
        System.out.println("3) verify(...) garante que interações aconteceram como esperado.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5 + Mockito)     ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import static org.mockito.Mockito.*;
    // import static org.junit.jupiter.api.Assertions.*;
    //
    // class BasicMockExampleTest {
    //
    //     interface BookRepository {
    //         int countBooks();
    //     }
    //
    //     static class LibraryService {
    //         private final BookRepository repository;
    //
    //         LibraryService(BookRepository repository) {
    //             this.repository = repository;
    //         }
    //
    //         boolean isEmpty() {
    //             return repository.countBooks() == 0;
    //         }
    //     }
    //
    //     @Test
    //     void deveUsarMockParaSimularRepositorio() {
    //         BookRepository repo = mock(BookRepository.class);
    //         when(repo.countBooks()).thenReturn(0);
    //
    //         LibraryService service = new LibraryService(repo);
    //
    //         assertTrue(service.isEmpty());
    //         verify(repo).countBooks();
    //     }
    // }
}

