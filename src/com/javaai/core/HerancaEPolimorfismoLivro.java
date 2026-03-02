package com.javaai.core;

/**
 * HerancaEPolimorfismoLivro
 *
 * Objetivo:
 *  - Demonstrar herança e polimorfismo usando o domínio de livros.
 *
 * Conceitos abordados:
 *  - Classe base Book.
 *  - Subclasses PhysicalBook e EBook.
 *  - Sobrescrita de método (override) e chamada polimórfica.
 */
public class HerancaEPolimorfismoLivro {

    static class Book {
        private final String title;

        Book(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public String getDetails() {
            return "Livro genérico: " + title;
        }
    }

    static class PhysicalBook extends Book {
        private final int pages;

        PhysicalBook(String title, int pages) {
            super(title);
            this.pages = pages;
        }

        @Override
        public String getDetails() {
            return "Livro físico: %s (%d páginas)".formatted(getTitle(), pages);
        }
    }

    static class EBook extends Book {
        private final double fileSizeMb;

        EBook(String title, double fileSizeMb) {
            super(title);
            this.fileSizeMb = fileSizeMb;
        }

        @Override
        public String getDetails() {
            return "E-book: %s (%.2f MB)".formatted(getTitle(), fileSizeMb);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Herança e polimorfismo com livros ===");

        Book generic = new Book("Java Básico");
        Book physical = new PhysicalBook("Clean Code", 450);
        Book ebook = new EBook("Effective Java", 5.2);

        // Chamada polimórfica: mesmo método, comportamentos diferentes.
        Book[] shelf = {generic, physical, ebook};
        for (Book book : shelf) {
            System.out.println(book.getDetails());
        }

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveUsarVersaoDaSubclasse() {
        //     Book book = new PhysicalBook("Clean Code", 450);
        //     String detalhes = book.getDetails();
        //     assertTrue(detalhes.contains("físico"));
        // }
        //
        // @Test
        // void deveChamarMetodosCorretosParaCadaTipo() {
        //     Book[] shelf = {
        //         new Book("Genérico"),
        //         new PhysicalBook("Clean Code", 450),
        //         new EBook("Effective Java", 5.2)
        //     };
        //     assertEquals(3, shelf.length);
        // }
    }
}

