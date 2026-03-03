package com.javaai.database;

import java.util.List;

/**
 * DatabaseIntegrador
 *
 * Objetivo:
 *  - Programa integrador da seção "database".
 *  - Combina:
 *    - modelagem conceitual NoSQL
 *    - abstração de repositório para MongoDB
 *
 * Observação:
 *  - Não há acesso real a MongoDB aqui; o foco é desenho de modelos e APIs.
 */
public class DatabaseIntegrador {

    public static void main(String[] args) {
        System.out.println("=== Database Integrador (NoSQL/Mongo) ===");

        // Modelagem conceitual (veja NoSqlModelagemBiblioteca).
        NoSqlModelagemBiblioteca.Library library = new NoSqlModelagemBiblioteca.Library();
        library.id = "lib-1";
        library.name = "Biblioteca Central";
        System.out.println("Library conceitual: " + library.name);

        // Abstração de repositório em memória (pensando em Mongo).
        RepositoryAbstracaoMongo.BookRepository repo = new RepositoryAbstracaoMongo.InMemoryBookRepository();
        RepositoryAbstracaoMongo.Book b1 = new RepositoryAbstracaoMongo.Book();
        b1.id = "1";
        b1.title = "Clean Code";
        b1.authorId = "a1";
        repo.save(b1);

        List<RepositoryAbstracaoMongo.Book> byAuthor = repo.findByAuthorId("a1");
        System.out.println("Livros do autor a1: " + byAuthor.size());

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void integradorDeveExecutarSemErros() {
        //     assertDoesNotThrow(() -> DatabaseIntegrador.main(new String[0]));
        // }
    }
}

