package com.javaai.modern;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * InterfacesFuncionaisELambdas
 *
 * Objetivo:
 *  - Consolidar os fundamentos de programação funcional no Java:
 *    - lambdas
 *    - method references
 *    - interfaces funcionais do pacote java.util.function
 *
 * Pontos de especialista:
 *  - Lambdas capturam variáveis effectively final.
 *  - Prefira method references quando aumentarem legibilidade.
 *  - Não confunda "imutabilidade" com "effectively final": é regra do compilador, não do objeto.
 *
 * Domínio: biblioteca/livros.
 */
public class InterfacesFuncionaisELambdas {

    static class Book {
        private final String title;
        private final double price;

        Book(String title, double price) {
            this.title = Objects.requireNonNull(title);
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Book{title='%s', price=%.2f}".formatted(title, price);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Interfaces funcionais e lambdas ===");

        List<Book> books = new ArrayList<>(List.of(
                new Book("Effective Java", 120.00),
                new Book("Clean Code", 95.00),
                new Book("DDD", 150.00)
        ));

        // Predicate<T>: recebe T e retorna boolean (filtros)
        Predicate<Book> isExpensive = book -> book.getPrice() >= 100.0;

        // Function<T, R>: transforma T em R (mapeamento)
        Function<Book, String> toTitle = Book::getTitle; // method reference

        // Consumer<T>: consome T (efeito colateral, ex.: imprimir/logar)
        Consumer<String> printLine = System.out::println;

        System.out.println("\nLivros caros (>=100):");
        for (Book book : books) {
            if (isExpensive.test(book)) {
                String title = toTitle.apply(book);
                printLine.accept("- " + title);
            }
        }

        // Supplier<T>: fornece um valor (lazy creation, defaults, factories)
        Supplier<Book> defaultBookSupplier = () -> new Book("Livro Padrão", 0.0);
        Book defaultBook = defaultBookSupplier.get();
        System.out.println("\nSupplier -> " + defaultBook);

        // BiFunction<A, B, R>: recebe dois valores e retorna um resultado
        BiFunction<Double, Double, Double> aplicarDesconto = (price, percent) -> price * (1.0 - percent);
        double priceWithDiscount = aplicarDesconto.apply(120.0, 0.15);
        System.out.println("\nBiFunction desconto: 120 com 15% = " + priceWithDiscount);

        // Comparator com lambda / method reference
        books.sort(Comparator.comparingDouble(Book::getPrice).reversed());
        System.out.println("\nOrdenado por preço (desc):");
        books.forEach(System.out::println);

        // Captura de variável: a variável deve ser "effectively final".
        // Ou seja, não pode ser reatribuída após ser capturada pela lambda.
        double extraFee = 5.0; // effectively final (não reatribuiremos)
        Function<Book, Double> finalPrice = b -> b.getPrice() + extraFee;
        System.out.println("\nPreço final com taxa fixa (" + extraFee + "):");
        books.forEach(b -> System.out.println(b.getTitle() + " -> " + finalPrice.apply(b)));

        // Descomente para ver o erro de compilação:
        // extraFee = 10.0; // reatribuição quebra effectively final

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void predicateDeveIdentificarLivroCaro() {
        //     Predicate<Book> isExpensive = b -> b.getPrice() >= 100.0;
        //     assertTrue(isExpensive.test(new Book("X", 100.0)));
        //     assertFalse(isExpensive.test(new Book("Y", 99.9)));
        // }
        //
        // @Test
        // void functionDeveExtrairTitulo() {
        //     Function<Book, String> toTitle = Book::getTitle;
        //     assertEquals("Clean Code", toTitle.apply(new Book("Clean Code", 95.0)));
        // }
    }
}

