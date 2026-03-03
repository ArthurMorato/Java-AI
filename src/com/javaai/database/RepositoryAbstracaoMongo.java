package com.javaai.database;

import java.util.List;
import java.util.Optional;

/**
 * RepositoryAbstracaoMongo
 *
 * Objetivo:
 *  - Mostrar como seria a abstração de repositórios pensando em MongoDB,
 *    sem acoplar ao driver específico.
 *
 * Ideia:
 *  - Interfaces de repositório focadas no domínio.
 *  - Implementações concretas (Mongo, in-memory, etc.) podem vir depois.
 */
public class RepositoryAbstracaoMongo {

    // Modelos mínimos, reutilizando a ideia de NoSqlModelagemBiblioteca.
    static class Book {
        String id;
        String title;
        String authorId;
    }

    interface BookRepository {
        void save(Book book);

        Optional<Book> findById(String id);

        List<Book> findByAuthorId(String authorId);
    }

    /**
     * Implementação fake em memória, didática.
     * Em um projeto real, haveria uma implementação com driver Mongo.
     */
    static class InMemoryBookRepository implements BookRepository {
        private final java.util.Map<String, Book> data = new java.util.HashMap<>();

        @Override
        public void save(Book book) {
            data.put(book.id, book);
        }

        @Override
        public Optional<Book> findById(String id) {
            return Optional.ofNullable(data.get(id));
        }

        @Override
        public List<Book> findByAuthorId(String authorId) {
            return data.values().stream()
                    .filter(b -> b.authorId.equals(authorId))
                    .toList();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Abstração de repositório pensando em MongoDB ===");

        BookRepository repo = new InMemoryBookRepository();
        Book book = new Book();
        book.id = "1";
        book.title = "Clean Code";
        book.authorId = "a1";
        repo.save(book);

        System.out.println("findById: " + repo.findById("1").map(b -> b.title).orElse("<não encontrado>"));
        System.out.println("findByAuthorId(a1): " + repo.findByAuthorId("a1").size() + " livro(s)");

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void repositoryDeveSalvarERecuperarLivro() {
        //     BookRepository repo = new InMemoryBookRepository();
        //     Book book = new Book();
        //     book.id = "1";
        //     book.title = "Clean Code";
        //     book.authorId = "a1";
        //     repo.save(book);
        //     assertTrue(repo.findById("1").isPresent());
        // }
    }
}

