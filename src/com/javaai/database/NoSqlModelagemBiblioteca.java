package com.javaai.database;

import java.util.List;

/**
 * NoSqlModelagemBiblioteca
 *
 * Objetivo:
 *  - Explicar modelagem NoSQL (MongoDB) para o domínio de biblioteca.
 *
 * Importante:
 *  - Não usamos driver MongoDB aqui para evitar dependências extras.
 *  - O foco é conceitual: como os modelos Java se relacionam com documentos/coleções.
 */
public class NoSqlModelagemBiblioteca {

    /**
     * Em MongoDB, poderíamos ter uma coleção "books" com documentos como:
     * {
     *   _id: "1",
     *   title: "Clean Code",
     *   author: { id: "a1", name: "Robert Martin" },
     *   year: 2008,
     *   genre: "Boas Práticas",
     *   price: 95.0
     * }
     */
    static class Author {
        String id;
        String name;
        String nationality;
        int birthYear;
    }

    static class Book {
        String id;
        String title;
        Author author;
        int year;
        String genre;
        double price;
    }

    /**
     * Coleção "libraries" pode embutir (embed) os livros.
     */
    static class Library {
        String id;
        String name;
        List<Book> books;
    }

    public static void main(String[] args) {
        System.out.println("=== Modelagem NoSQL (conceitual) ===");
        System.out.println("Pense em coleções: authors, books, libraries, members.");
        System.out.println("Avalie embed vs reference conforme a cardinalidade e uso de leitura.");

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveCriarModeloSimplesDeLibrary() {
        //     Library lib = new Library();
        //     lib.id = "lib-1";
        //     lib.name = "Biblioteca Central";
        //     assertEquals("Biblioteca Central", lib.name);
        // }
    }
}

