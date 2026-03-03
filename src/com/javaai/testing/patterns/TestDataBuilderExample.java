package com.javaai.testing.patterns;

/**
 * TestDataBuilderExample
 *
 * Objetivo:
 *  - Mostrar o padrão Test Data Builder para criação de dados de teste
 *    legíveis e reutilizáveis.
 */
public class TestDataBuilderExample {

    public static void main(String[] args) {
        System.out.println("=== Padrão de teste – Test Data Builder ===");
        System.out.println("Use builders específicos para testes para reduzir duplicação");
        System.out.println("e deixar CLARO o que é relevante no cenário.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5)               ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import static org.junit.jupiter.api.Assertions.*;
    //
    // class TestDataBuilderExampleTest {
    //
    //     static class Book {
    //         String id;
    //         String title;
    //         String author;
    //         int year;
    //     }
    //
    //     static class BookBuilder {
    //         private String id = "1";
    //         private String title = "Clean Code";
    //         private String author = "Robert Martin";
    //         private int year = 2008;
    //
    //         BookBuilder withId(String id) {
    //             this.id = id;
    //             return this;
    //         }
    //
    //         BookBuilder withTitle(String title) {
    //             this.title = title;
    //             return this;
    //         }
    //
    //         BookBuilder withAuthor(String author) {
    //             this.author = author;
    //             return this;
    //         }
    //
    //         BookBuilder withYear(int year) {
    //             this.year = year;
    //             return this;
    //         }
    //
    //         Book build() {
    //             Book b = new Book();
    //             b.id = id;
    //             b.title = title;
    //             b.author = author;
    //             b.year = year;
    //             return b;
    //         }
    //     }
    //
    //     @Test
    //     void deveCriarLivroComBuilderDeTeste() {
    //         Book book = new BookBuilder()
    //                 .withTitle("Effective Java")
    //                 .withYear(2018)
    //                 .build();
    //
    //         assertEquals("Effective Java", book.title);
    //         assertEquals(2018, book.year);
    //     }
    // }
}

