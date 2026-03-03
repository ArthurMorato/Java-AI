package com.javaai.testing.mockito;

/**
 * InjectMocksExample
 *
 * Objetivo:
 *  - Explicar o uso de @InjectMocks e @Mock com Mockito + JUnit 5.
 *
 * Ideia:
 *  - @Mock cria dublês para dependências.
 *  - @InjectMocks cria a classe-alvo injetando automaticamente os mocks.
 */
public class InjectMocksExample {

    public static void main(String[] args) {
        System.out.println("=== Mockito – @InjectMocks + @Mock ===");
        System.out.println("Use @InjectMocks para montar automaticamente o objeto sob teste com seus mocks.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5 + Mockito)     ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import org.junit.jupiter.api.extension.ExtendWith;
    // import org.mockito.InjectMocks;
    // import org.mockito.Mock;
    // import org.mockito.junit.jupiter.MockitoExtension;
    // import static org.mockito.Mockito.*;
    // import static org.junit.jupiter.api.Assertions.*;
    //
    // @ExtendWith(MockitoExtension.class)
    // class InjectMocksExampleTest {
    //
    //     interface BookRepository {
    //         int count();
    //     }
    //
    //     static class LibraryStatsService {
    //         private final BookRepository repository;
    //
    //         LibraryStatsService(BookRepository repository) {
    //             this.repository = repository;
    //         }
    //
    //         boolean hasBooks() {
    //             return repository.count() > 0;
    //         }
    //     }
    //
    //     @Mock
    //     BookRepository repository;
    //
    //     @InjectMocks
    //     LibraryStatsService service;
    //
    //     @Test
    //     void deveInjetarMocksAutomaticamente() {
    //         when(repository.count()).thenReturn(10);
    //         assertTrue(service.hasBooks());
    //         verify(repository).count();
    //     }
    // }
}

