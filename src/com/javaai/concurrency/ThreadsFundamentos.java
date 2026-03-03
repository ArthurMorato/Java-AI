package com.javaai.concurrency;

/**
 * ThreadsFundamentos
 *
 * Objetivo:
 *  - Explicar o básico de threads em Java:
 *    criação, início, finalização e problemas clássicos (race condition).
 *
 * Pontos de especialista:
 *  - Thread é um recurso caro; crie poucas e reutilize (ver Executors).
 *  - Evite compartilhar estado mutável sem sincronização adequada.
 *  - Entenda que ordem de execução NÃO é garantida.
 *
 * Domínio: biblioteca contabilizando empréstimos em paralelo.
 */
public class ThreadsFundamentos {

    /**
     * Simula um contador de empréstimos da biblioteca sem sincronização.
     * Este exemplo é propositalmente "errado" para demonstrar race condition.
     */
    static class LoanCounter {
        private int totalLoans = 0;

        void increment() {
            // NÃO use este padrão em código de produção sem sincronização.
            int current = totalLoans;
            // Simula algum trabalho
            current = current + 1;
            totalLoans = current;
        }

        int getTotalLoans() {
            return totalLoans;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Threads: criação e race condition ===");

        LoanCounter counter = new LoanCounter();

        // Criamos duas threads que simulam empréstimos simultâneos.
        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task, "loan-thread-1");
        Thread t2 = new Thread(task, "loan-thread-2");

        t1.start();
        t2.start();

        // Aguarda as threads terminarem
        t1.join();
        t2.join();

        System.out.println("Valor esperado (sem concorrência): 20000");
        System.out.println("Valor real (com race condition):   " + counter.getTotalLoans());

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveMostrarRaceConditionNaPratica() throws InterruptedException {
        //     LoanCounter counter = new LoanCounter();
        //     Runnable task = () -> {
        //         for (int i = 0; i < 10_000; i++) {
        //             counter.increment();
        //         }
        //     };
        //     Thread t1 = new Thread(task);
        //     Thread t2 = new Thread(task);
        //     t1.start();
        //     t2.start();
        //     t1.join();
        //     t2.join();
        //     assertNotEquals(20_000, counter.getTotalLoans());
        // }
    }
}

