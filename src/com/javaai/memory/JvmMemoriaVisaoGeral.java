package com.javaai.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * JvmMemoriaVisaoGeral
 *
 * Objetivo:
 *  - Explicar, de forma didática, a organização básica de memória da JVM:
 *    - heap
 *    - stack
 *    - metaspace
 *
 * Pontos de especialista:
 *  - Variáveis locais e chamadas de método vivem na stack.
 *  - Objetos (new ...) vivem na heap.
 *  - Classes e metadados vivem na metaspace.
 *
 * Domínio: criação de muitos objetos Book simulando um catálogo.
 */
public class JvmMemoriaVisaoGeral {

    static class Book {
        String id;
        String title;

        Book(String id, String title) {
            this.id = id;
            this.title = title;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== JVM: visão geral de memória ===");

        // stack: referência local catalog
        List<Book> catalog = new ArrayList<>();

        for (int i = 0; i < 100_000; i++) {
            // heap: cada new Book é um objeto na heap
            catalog.add(new Book(String.valueOf(i), "Livro " + i));
        }

        System.out.println("Tamanho do catálogo: " + catalog.size());

        // Mostra informações aproximadas de memória via Runtime.
        Runtime runtime = Runtime.getRuntime();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long used = total - free;
        System.out.println("Memória total (bytes): " + total);
        System.out.println("Memória usada  (bytes): " + used);

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveCriarMuitosLivrosNaHeap() {
        //     List<Book> catalog = new ArrayList<>();
        //     for (int i = 0; i < 100_000; i++) {
        //         catalog.add(new Book(String.valueOf(i), "Livro " + i));
        //     }
        //     assertEquals(100_000, catalog.size());
        // }
    }
}

