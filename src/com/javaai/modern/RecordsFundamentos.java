package com.javaai.modern;

import java.time.Year;
import java.util.Objects;

/**
 * RecordsFundamentos
 *
 * Objetivo:
 *  - Ensinar records (Java 16+) como "data carriers" imutáveis.
 *
 * Pontos de especialista:
 *  - Record gera automaticamente: fields final, construtor, accessors, equals/hashCode/toString.
 *  - Use compact constructor para validações e normalização.
 *  - Record não é "entidade" de domínio por padrão; é excelente para DTOs, mensagens, snapshots.
 *  - Record é final: não pode ser estendido.
 *
 * Domínio: biblioteca/livros.
 */
public class RecordsFundamentos {

    /**
     * Exemplo de record: um livro como "snapshot imutável".
     *
     * Observe a validação no construtor compacto:
     *  - garante invariantes na criação.
     */
    record Book(String id, String title, String author, int year, double price) {
        Book {
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("id não pode ser vazio");
            }
            Objects.requireNonNull(title, "title não pode ser null");
            Objects.requireNonNull(author, "author não pode ser null");
            if (year < 0 || year > Year.now().getValue()) {
                throw new IllegalArgumentException("year inválido: " + year);
            }
            if (price < 0.0) {
                throw new IllegalArgumentException("price não pode ser negativo");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Records: imutabilidade e validação ===");

        Book book = new Book("1", "Effective Java", "Joshua Bloch", 2018, 120.00);
        System.out.println("Record: " + book);

        // Accessors: id(), title()... (não são getters com prefixo get)
        System.out.println("Título: " + book.title());
        System.out.println("Preço: " + book.price());

        // equals/hashCode corretos por valor (componentes do record)
        Book same = new Book("1", "Effective Java", "Joshua Bloch", 2018, 120.00);
        System.out.println("Equals por valor? " + book.equals(same));

        // Imutabilidade: não há setters; para "alterar" cria-se um novo record.
        Book withDiscount = new Book(book.id(), book.title(), book.author(), book.year(), book.price() * 0.9);
        System.out.println("Com desconto: " + withDiscount);

        // Descomente para ver validação em ação:
        // Book invalid = new Book("", null, "X", 3000, -1);

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void recordsDevemSerIguaisPorValor() {
        //     Book a = new Book("1", "T", "A", 2020, 10);
        //     Book b = new Book("1", "T", "A", 2020, 10);
        //     assertEquals(a, b);
        // }
        //
        // @Test
        // void deveValidarIdNaoVazio() {
        //     assertThrows(IllegalArgumentException.class,
        //         () -> new Book("", "T", "A", 2020, 10));
        // }
    }
}

