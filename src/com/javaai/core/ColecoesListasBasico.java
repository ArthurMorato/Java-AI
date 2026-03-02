package com.javaai.core;

import java.util.ArrayList;
import java.util.List;

/**
 * ColecoesListasBasico
 *
 * Objetivo:
 *  - Introduzir o uso de List em Java com ArrayList.
 *  - Mostrar operações essenciais: adicionar, remover, buscar e iterar.
 *
 * Conceitos abordados:
 *  - Interface List e implementação ArrayList.
 *  - Tipagem forte: List<Book> em vez de List sem generics.
 *  - Laços for tradicional, for-each e forEach com lambda.
 */
public class ColecoesListasBasico {

    /**
     * Tipo de domínio reutilizado para exemplificar listas.
     */
    static class Book {
        private final String id;
        private final String title;

        Book(String id, String title) {
            this.id = id;
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Book{id='%s', title='%s'}".formatted(id, title);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Coleções em Java: List e ArrayList ===");

        // Criação de uma lista vazia de livros.
        // Usamos a interface List na variável e ArrayList como implementação.
        List<Book> books = new ArrayList<>();

        // Adicionando elementos
        books.add(new Book("1", "Clean Code"));
        books.add(new Book("2", "Effective Java"));
        books.add(new Book("3", "Java Concurrency in Practice"));

        System.out.println("\nLista inicial de livros:");
        for (Book book : books) {
            System.out.println(book);
        }

        // Acessando um elemento pelo índice (começa em 0)
        Book first = books.get(0);
        System.out.println("\nPrimeiro livro da lista: " + first.getTitle());

        // Removendo um livro pelo índice
        books.remove(1); // remove "Effective Java"

        System.out.println("\nApós remover o segundo livro:");
        books.forEach(System.out::println);

        // Buscando livro por id (exemplo simples de busca linear)
        String idBuscado = "3";
        Book found = null;
        for (Book book : books) {
            if (book.getId().equals(idBuscado)) {
                found = book;
                break;
            }
        }
        System.out.println("\nLivro com id=" + idBuscado + ": " + found);

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveAdicionarLivrosNaLista() {
        //     List<Book> books = new ArrayList<>();
        //     books.add(new Book("1", "Clean Code"));
        //     books.add(new Book("2", "Effective Java"));
        //     assertEquals(2, books.size());
        // }
        //
        // @Test
        // void deveRemoverLivroPeloIndice() {
        //     List<Book> books = new ArrayList<>();
        //     books.add(new Book("1", "Clean Code"));
        //     books.add(new Book("2", "Effective Java"));
        //     books.remove(0);
        //     assertEquals(1, books.size());
        //     assertEquals("Effective Java", books.get(0).getTitle());
        // }
    }
}

