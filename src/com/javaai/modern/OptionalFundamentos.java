package com.javaai.modern;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * OptionalFundamentos
 *
 * Objetivo:
 *  - Ensinar Optional (Java 8+) de forma correta e "nível especialista":
 *    quando usar, quando NÃO usar e como evitar NullPointerException sem criar
 *    código difícil de ler.
 *
 * Regras práticas (essenciais para especialista):
 *  - Optional é ótimo como "retorno" de método: sinaliza ausência de valor.
 *  - Evite usar Optional como campo de classe (na maioria dos casos é ruído).
 *  - Evite Optional em parâmetros (geralmente piora a API).
 *  - Não use get() sem checar presença (é um "NullPointerException disfarçado").
 *
 * Domínio: biblioteca/livros.
 */
public class OptionalFundamentos {

    static class Book {
        private final String id;
        private final String title;
        private final String author;

        Book(String id, String title, String author) {
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

        public String getAuthor() {
            return author;
        }

        @Override
        public String toString() {
            return "Book{id='%s', title='%s', author='%s'}".formatted(id, title, author);
        }
    }

    /**
     * Simula um repositório em memória.
     * Em projetos reais, isso seria uma interface e uma implementação (DB, API etc).
     */
    static class BookRepository {
        private final Map<String, Book> byId = new HashMap<>();

        public void save(Book book) {
            byId.put(book.getId(), book);
        }

        /**
         * Bom uso de Optional: retorno indicando "pode não existir".
         */
        public Optional<Book> findById(String id) {
            return Optional.ofNullable(byId.get(id));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Optional: uso correto e armadilhas ===");

        BookRepository repo = new BookRepository();
        repo.save(new Book("1", "Effective Java", "Joshua Bloch"));

        Optional<Book> maybeBook = repo.findById("1");
        Optional<Book> missing = repo.findById("999");

        // 1) ifPresent / ifPresentOrElse: fluxo explícito e seguro.
        maybeBook.ifPresent(book -> System.out.println("Encontrado: " + book));
        missing.ifPresentOrElse(
                book -> System.out.println("Encontrado: " + book),
                () -> System.out.println("Não encontrado (id=999)")
        );

        // 2) orElse vs orElseGet: diferença de custo/efeito colateral.
        // orElse(...) avalia o argumento SEMPRE (mesmo se o Optional tiver valor).
        // orElseGet(...) avalia SOMENTE se estiver vazio.
        Book fallback1 = maybeBook.orElse(criarFallbackCaro());     // fallback criado mesmo sem precisar
        Book fallback2 = missing.orElseGet(OptionalFundamentos::criarFallbackCaro); // criado só quando necessário
        System.out.println("fallback1: " + fallback1.getTitle());
        System.out.println("fallback2: " + fallback2.getTitle());

        // 3) map/flatMap: transformar valores sem null-checks.
        String authorUpper = repo.findById("1")
                .map(Book::getAuthor)          // Book -> String
                .map(String::toUpperCase)      // String -> String
                .orElse("AUTOR DESCONHECIDO");
        System.out.println("Autor (uppercase): " + authorUpper);

        // 4) orElseThrow: quando a ausência é erro de regra de negócio.
        // Use uma exceção expressiva. (Em APIs, pode virar 404; em domínio, uma DomainException etc.)
        Book mustExist = repo.findById("1")
                .orElseThrow(() -> new IllegalStateException("Livro deveria existir (id=1)"));
        System.out.println("Livro obrigatório: " + mustExist.getTitle());

        // 5) O que NÃO fazer: get() sem checar.
        // Descomente para ver o problema: NoSuchElementException.
        // Book boom = missing.get();

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void findByIdDeveRetornarOptionalVazioQuandoNaoExiste() {
        //     BookRepository repo = new BookRepository();
        //     Optional<Book> result = repo.findById("x");
        //     assertTrue(result.isEmpty());
        // }
        //
        // @Test
        // void orElseThrowDeveRetornarLivroQuandoExiste() {
        //     BookRepository repo = new BookRepository();
        //     repo.save(new Book("1", "Effective Java", "Joshua Bloch"));
        //     Book book = repo.findById("1").orElseThrow();
        //     assertEquals("Effective Java", book.getTitle());
        // }
    }

    private static Book criarFallbackCaro() {
        // Simula um fallback "caro": criar objeto, montar defaults, buscar em outro serviço, etc.
        System.out.println("Criando fallback caro...");
        return new Book("fallback", "Livro Padrão", "Sistema");
    }
}

