package com.javaai.core;

/**
 * EncapsulamentoLivro
 *
 * Objetivo:
 *  - Focar exclusivamente em encapsulamento em Java usando o domínio de livros.
 *
 * Conceitos abordados:
 *  - Campos privados.
 *  - Getters (e opcionalmente setters) para controlar acesso.
 *  - Validação simples no construtor.
 */
public class EncapsulamentoLivro {

    /**
     * Livro com campos privados e exposição controlada via métodos públicos.
     */
    static class Book {
        private final String id;
        private String title;
        private String author;

        Book(String id, String title, String author) {
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("id não pode ser vazio");
            }
            this.id = id;
            this.title = title;
            this.author = author;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String summary() {
            return "Livro: %s (%s)".formatted(title, author);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Encapsulamento em Java com Book ===");

        Book book = new Book("1", "Clean Code", "Robert Martin");

        System.out.println("Resumo inicial: " + book.summary());

        // Atualizando título e autor via métodos públicos (setters).
        book.setTitle("Clean Code (2ª Edição)");
        book.setAuthor("Robert C. Martin");

        System.out.println("Após atualização: " + book.summary());

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveAtualizarTituloComSetTitle() {
        //     Book book = new Book("1", "Clean Code", "Robert Martin");
        //     book.setTitle("Clean Code (2ª Edição)");
        //     assertEquals("Clean Code (2ª Edição)", book.getTitle());
        // }
        //
        // @Test
        // void deveLancarExcecaoQuandoIdVazio() {
        //     assertThrows(IllegalArgumentException.class,
        //         () -> new Book("", "Título", "Autor"));
        // }
    }
}

