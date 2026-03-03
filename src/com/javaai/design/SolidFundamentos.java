package com.javaai.design;

import java.util.ArrayList;
import java.util.List;

/**
 * SolidFundamentos
 *
 * Objetivo:
 *  - Ilustrar os princípios SOLID de forma simples no domínio de biblioteca.
 *
 * Foco principal neste exemplo:
 *  - SRP (Single Responsibility Principle)
 *  - OCP (Open/Closed Principle)
 */
public class SolidFundamentos {

    /**
     * SRP: Book representa somente dados do livro.
     */
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
    }

    /**
     * SRP: BookRepository cuida apenas de persistência (aqui em memória).
     */
    interface BookRepository {
        void save(Book book);

        List<Book> findAll();
    }

    static class InMemoryBookRepository implements BookRepository {
        private final List<Book> data = new ArrayList<>();

        @Override
        public void save(Book book) {
            data.add(book);
        }

        @Override
        public List<Book> findAll() {
            return List.copyOf(data);
        }
    }

    /**
     * OCP: Serviço aberto à extensão de regras de preço através de estratégia.
     */
    interface PricingStrategy {
        double priceFor(Book book);
    }

    static class StandardPricingStrategy implements PricingStrategy {
        @Override
        public double priceFor(Book book) {
            return 100.0;
        }
    }

    static class BestSellerPricingStrategy implements PricingStrategy {
        @Override
        public double priceFor(Book book) {
            return 150.0;
        }
    }

    static class BookPricingService {
        private final PricingStrategy strategy;

        BookPricingService(PricingStrategy strategy) {
            this.strategy = strategy;
        }

        double calculatePrice(Book book) {
            return strategy.priceFor(book);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SOLID: SRP e OCP na prática ===");

        BookRepository repo = new InMemoryBookRepository();
        repo.save(new Book("1", "Clean Code", "Robert Martin"));
        repo.save(new Book("2", "Effective Java", "Joshua Bloch"));

        BookPricingService standardService = new BookPricingService(new StandardPricingStrategy());
        BookPricingService bestSellerService = new BookPricingService(new BestSellerPricingStrategy());

        for (Book book : repo.findAll()) {
            System.out.printf("Livro: %s | padrão=%.2f | best-seller=%.2f%n",
                    book.getTitle(),
                    standardService.calculatePrice(book),
                    bestSellerService.calculatePrice(book));
        }

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void bestSellerDeveSerMaisCaroQueStandard() {
        //     Book book = new Book("1", "Clean Code", "Robert Martin");
        //     PricingStrategy standard = new StandardPricingStrategy();
        //     PricingStrategy bestSeller = new BestSellerPricingStrategy();
+        //     assertTrue(bestSeller.priceFor(book) > standard.priceFor(book));
        // }
    }
}

