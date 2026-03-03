package com.javaai.design;

/**
 * DesignCreationalFactory
 *
 * Objetivo:
 *  - Demonstrar o padrão Factory Method no domínio de livros.
 *
 * Papel do padrão:
 *  - Centralizar a lógica de criação de objetos, permitindo:
 *    - encapsular regras
 *    - variar implementações concretas sem mudar o cliente
 */
public class DesignCreationalFactory {

    interface Book {
        String title();
    }

    static class PhysicalBook implements Book {
        private final String title;

        PhysicalBook(String title) {
            this.title = title;
        }

        @Override
        public String title() {
            return title + " (Físico)";
        }
    }

    static class EBook implements Book {
        private final String title;

        EBook(String title) {
            this.title = title;
        }

        @Override
        public String title() {
            return title + " (E-book)";
        }
    }

    interface BookFactory {
        Book create(String baseTitle);
    }

    static class PhysicalBookFactory implements BookFactory {
        @Override
        public Book create(String baseTitle) {
            return new PhysicalBook(baseTitle);
        }
    }

    static class EBookFactory implements BookFactory {
        @Override
        public Book create(String baseTitle) {
            return new EBook(baseTitle);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Factory Method com livros ===");

        BookFactory physicalFactory = new PhysicalBookFactory();
        BookFactory ebookFactory = new EBookFactory();

        Book b1 = physicalFactory.create("Clean Code");
        Book b2 = ebookFactory.create("Effective Java");

        System.out.println(b1.title());
        System.out.println(b2.title());

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void factoryDeveCriarLivrosDiferentes() {
        //     BookFactory physical = new PhysicalBookFactory();
        //     BookFactory ebook = new EBookFactory();
        //     assertTrue(physical.create("X").title().contains("Físico"));
        //     assertTrue(ebook.create("Y").title().contains("E-book"));
        // }
    }
}

