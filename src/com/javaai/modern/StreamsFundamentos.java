package com.javaai.modern;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * StreamsFundamentos
 *
 * Objetivo:
 *  - Ensinar Streams (Java 8+) como ferramenta para processamento de coleções com foco em:
 *    - expressividade
 *    - imutabilidade de resultados (quando possível)
 *    - clareza de pipeline
 *
 * Pontos de especialista:
 *  - Streams são "single-use" (não reusar o mesmo stream).
 *  - Operações intermediárias (map/filter/sorted) são lazy.
 *  - Operações terminais (toList/collect/reduce/forEach) disparam o processamento.
 *  - Cuidado com efeitos colaterais em pipelines (principalmente com parallel()).
 *
 * Domínio: biblioteca/livros.
 */
public class StreamsFundamentos {

    static class Book {
        private final String id;
        private final String title;
        private final String author;
        private final int year;
        private final String genre;
        private final double price;

        Book(String id, String title, String author, int year, String genre, double price) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.year = year;
            this.genre = genre;
            this.price = price;
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

        public int getYear() {
            return year;
        }

        public String getGenre() {
            return genre;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Book{id='%s', title='%s', author='%s', year=%d, genre='%s', price=%.2f}"
                    .formatted(id, title, author, year, genre, price);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Streams: pipelines, collectors e boas práticas ===");

        List<Book> books = sampleBooks();

        // 1) Filter + map + toList (Java 16+: Stream#toList retorna lista imutável na prática)
        List<String> expensiveTitles = books.stream()
                .filter(b -> b.getPrice() >= 100.0)
                .map(Book::getTitle)
                .toList();
        System.out.println("\nTítulos com preço >= 100: " + expensiveTitles);

        // 2) sorted + limit: ordenação por preço (desc) e pega top N
        List<Book> top2ByPrice = books.stream()
                .sorted(Comparator.comparingDouble(Book::getPrice).reversed())
                .limit(2)
                .toList();
        System.out.println("\nTop 2 por preço:");
        top2ByPrice.forEach(System.out::println);

        // 3) distinct em dados primitivos: map para autor e Set final para unicidade
        Set<String> authors = books.stream()
                .map(Book::getAuthor)
                .collect(Collectors.toSet());
        System.out.println("\nAutores (únicos): " + authors);

        // 4) groupingBy: agrupar por gênero
        Map<String, List<Book>> byGenre = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre));
        System.out.println("\nAgrupado por gênero (quantidades):");
        byGenre.forEach((genre, list) -> System.out.println("- " + genre + ": " + list.size()));

        // 5) partitioningBy: particionar por uma condição (ex.: publicados a partir de 2015)
        Map<Boolean, List<Book>> partitioned = books.stream()
                .collect(Collectors.partitioningBy(b -> b.getYear() >= 2015));
        System.out.println("\nPartição year>=2015: " +
                "sim=" + partitioned.get(true).size() + ", não=" + partitioned.get(false).size());

        // 6) Summarizing: estatísticas em double
        DoubleSummaryStatistics priceStats = books.stream()
                .collect(Collectors.summarizingDouble(Book::getPrice));
        System.out.println("\nEstatísticas de preço: " + priceStats);

        // 7) Collector customizado (ex.: Map id -> Book)
        Map<String, Book> byId = books.stream()
                .collect(Collectors.toMap(Book::getId, b -> b));
        System.out.println("\nBusca por id=3 via Map: " + byId.get("3"));

        // 8) Exemplo do que evitar: mutação externa dentro do pipeline
        // (Aqui é apenas demonstrativo — prefira retornar coleções via collect/toList)
        List<String> antiPattern = new ArrayList<>();
        books.stream()
                .map(Book::getTitle)
                .forEach(antiPattern::add); // efeito colateral
        System.out.println("\nAnti-pattern (efeito colateral): " + antiPattern);

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveFiltrarLivrosCaros() {
        //     List<Book> books = sampleBooks();
        //     List<Book> caros = books.stream().filter(b -> b.getPrice() >= 100).toList();
        //     assertFalse(caros.isEmpty());
        // }
        //
        // @Test
        // void deveAgruparPorGenero() {
        //     Map<String, List<Book>> byGenre = sampleBooks().stream()
        //         .collect(Collectors.groupingBy(Book::getGenre));
        //     assertTrue(byGenre.containsKey("Arquitetura"));
        // }
    }

    private static List<Book> sampleBooks() {
        return List.of(
                new Book("1", "Effective Java", "Joshua Bloch", 2018, "Boas Práticas", 120.00),
                new Book("2", "Clean Code", "Robert Martin", 2008, "Boas Práticas", 95.00),
                new Book("3", "Domain-Driven Design", "Eric Evans", 2003, "Arquitetura", 150.00),
                new Book("4", "Java Concurrency in Practice", "Brian Goetz", 2006, "Concorrência", 180.00),
                new Book("5", "Modern Java in Action", "Raoul-Gabriel Urma", 2021, "Linguagem", 110.00)
        );
    }
}

