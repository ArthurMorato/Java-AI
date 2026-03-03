package com.javaai.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ConcurrencyIntegrador
 *
 * Objetivo:
 *  - Programa integrador da seção "concurrency".
 *  - Combina:
 *    - threads básicas
 *    - ExecutorService
 *    - locks
 *    - virtual threads
 *
 * Cenário:
 *  - Biblioteca processa:
 *    - contagem de empréstimos (threads)
 *    - geração de relatórios (executor)
 *    - atualização de estoque (lock)
 *    - requisições simuladas de API (virtual threads)
 */
public class ConcurrencyIntegrador {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Concurrency Integrador ===");

        // 1) Contagem de empréstimos em paralelo (reuso da ideia de ThreadsFundamentos)
        var counter = new ThreadsFundamentos.LoanCounter();
        Runnable loanTask = () -> {
            for (int i = 0; i < 5_000; i++) {
                counter.increment();
            }
        };
        Thread t1 = new Thread(loanTask, "loan-1");
        Thread t2 = new Thread(loanTask, "loan-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Total de empréstimos (com possível race condition): " + counter.getTotalLoans());

        // 2) Geração de relatórios com ExecutorService
        try (ExecutorService pool = Executors.newFixedThreadPool(3)) {
            List<Callable<String>> tasks = List.of(
                    () -> "Relatório diário gerado por " + Thread.currentThread().getName(),
                    () -> "Relatório semanal gerado por " + Thread.currentThread().getName(),
                    () -> "Relatório mensal gerado por " + Thread.currentThread().getName()
            );
            List<Future<String>> futures = new ArrayList<>();
            for (Callable<String> task : tasks) {
                futures.add(pool.submit(task));
            }
            for (Future<String> f : futures) {
                System.out.println(f.get());
            }
        }

        // 3) Atualização de estoque com lock
        var inventory = new LocksFundamentos.Inventory();
        Runnable stockTask = () -> {
            for (int i = 0; i < 10_000; i++) {
                inventory.addWithLock(1);
            }
        };
        Thread s1 = new Thread(stockTask);
        Thread s2 = new Thread(stockTask);
        s1.start();
        s2.start();
        s1.join();
        s2.join();
        System.out.println("Estoque final (com lock): " + inventory.getStock());

        // 4) Múltiplas requisições simuladas com virtual threads
        try (ExecutorService virtualExec = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; i++) {
                int id = i;
                virtualExec.submit(() ->
                        System.out.println("Requisição " + id + " processada em " + Thread.currentThread())
                );
            }
            virtualExec.shutdown();
        }

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void integradorDeveExecutarSemErros() {
        //     // Teste de fumaça: apenas garantir que o main não lança exceções.
        //     assertDoesNotThrow(() -> ConcurrencyIntegrador.main(new String[0]));
        // }
    }
}

