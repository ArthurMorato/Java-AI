package com.javaai.modern;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ModernIntegrador
 *
 * Objetivo:
 *  - Ser o programa integrador do módulo "modern", combinando:
 *    - records (modelagem imutável)
 *    - Optional (ausência explícita)
 *    - Streams (pipelines e collectors)
 *    - interfaces funcionais/lambdas (Functions, method refs)
 *    - sealed + pattern matching (regras fechadas de domínio)
 *
 * Ideia do exercício:
 *  - Simular um "catálogo" de livros e um fluxo de empréstimo com regras por tipo de membro.
 */
public class ModernIntegrador {

    // records: tipo imutável, excelente para snapshots/DTOs
    record Book(String id, String title, String author, int year, String genre, double price) { }

    // sealed: hierarquia fechada de membros
    sealed interface Member permits Student, Professor, Guest {
        String name();
    }
    record Student(String name) implements Member { }
    record Professor(String name) implements Member { }
    record Guest(String name) implements Member { }

    public static void main(String[] args) {
        System.out.println("=== Modern Integrador (Java 21+) ===");

        List<Book> catalog = List.of(
                new Book("1", "Effective Java", "Joshua Bloch", 2018, "Boas Práticas", 120.00),
                new Book("2", "Clean Code", "Robert Martin", 2008, "Boas Práticas", 95.00),
                new Book("3", "Domain-Driven Design", "Eric Evans", 2003, "Arquitetura", 150.00),
                new Book("4", "Modern Java in Action", "Raoul-Gabriel Urma", 2021, "Linguagem", 110.00),
                new Book("5", "Java Concurrency in Practice", "Brian Goetz", 2006, "Concorrência", 180.00)
        );

        // Streams + collectors: map autor -> quantidade de livros no catálogo
        Map<String, Long> countByAuthor = catalog.stream()
                .collect(Collectors.groupingBy(Book::author, Collectors.counting()));
        System.out.println("\nQtd por autor: " + countByAuthor);

        // Streams: top 3 por preço
        List<Book> top3 = catalog.stream()
                .sorted(Comparator.comparingDouble(Book::price).reversed())
                .limit(3)
                .toList();
        System.out.println("\nTop 3 por preço:");
        top3.forEach(System.out::println);

        // Optional: buscar por id de forma segura
        String idToFind = "4";
        Optional<Book> maybe = findById(catalog, idToFind);
        System.out.println("\nBusca id=" + idToFind + ": " + maybe.map(Book::title).orElse("<não encontrado>"));

        // Interfaces funcionais: Function para aplicar desconto (sem mutar o record original)
        Function<Book, Book> discount10 = b -> new Book(
                b.id(), b.title(), b.author(), b.year(), b.genre(), b.price() * 0.9
        );
        Book discounted = maybe.map(discount10).orElseThrow();
        System.out.println("\nCom desconto 10%: " + discounted);

        // Sealed + pattern matching: regra de empréstimo por tipo de membro
        Member member = new Student("Ana");
        System.out.println("\nRegras de empréstimo para " + member.name() + ": " + loanRules(member));

        // Exemplo final: tentativa de empréstimo (simples e didática)
        boolean canBorrow = canBorrow(member, 4); // suponha que já tem 4 livros
        System.out.println("Pode emprestar mais um? " + canBorrow);

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void findByIdDeveRetornarVazioQuandoNaoExiste() {
        //     List<Book> catalog = List.of(new Book("1","T","A",2020,"G",10));
        //     assertTrue(findById(catalog, "x").isEmpty());
        // }
        //
        // @Test
        // void loanRulesDeveSerDiferentePorTipo() {
        //     assertNotEquals(loanRules(new Student("S")), loanRules(new Professor("P")));
        // }
    }

    static Optional<Book> findById(List<Book> catalog, String id) {
        return catalog.stream().filter(b -> b.id().equals(id)).findFirst();
    }

    static String loanRules(Member member) {
        return switch (member) {
            case Student s -> "até 5 livros / 15 dias";
            case Professor p -> "até 10 livros / 30 dias";
            case Guest g -> "somente consulta local";
        };
    }

    static boolean canBorrow(Member member, int alreadyBorrowed) {
        int limit = switch (member) {
            case Student s -> 5;
            case Professor p -> 10;
            case Guest g -> 0;
        };
        return alreadyBorrowed < limit;
    }
}

