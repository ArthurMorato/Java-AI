package com.javaai.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * VirtualThreadsFundamentos
 *
 * Objetivo:
 *  - Demonstrar virtual threads (Project Loom, Java 21).
 *
 * Pontos de especialista:
 *  - Virtual threads são excelentes para I/O-bound massivo (muitas conexões/bloqueios).
 *  - Cada virtual thread é leve e gerenciado pela JVM (user-mode), não pelo SO.
 *  - A API de programação continua parecida com a de threads tradicionais.
 *
 * Domínio: muitas requisições simuladas à "API" da biblioteca.
 */
public class VirtualThreadsFundamentos {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Virtual Threads (Java 21) ===");

        int tasks = 10_000;

        // Executor tradicional com pool fixo
        try (ExecutorService platformPool = Executors.newFixedThreadPool(100)) {
            Instant start = Instant.now();
            for (int i = 0; i < tasks; i++) {
                platformPool.submit(VirtualThreadsFundamentos::simulateIoOperation);
            }
            platformPool.shutdown();
            while (!platformPool.isTerminated()) {
                Thread.sleep(10);
            }
            Instant end = Instant.now();
            System.out.println("Tempo com thread pool tradicional: " + Duration.between(start, end));
        }

        // Executor com virtual threads
        try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            Instant start = Instant.now();
            List<Runnable> runnables = new ArrayList<>();
            for (int i = 0; i < tasks; i++) {
                runnables.add(VirtualThreadsFundamentos::simulateIoOperation);
            }
            for (Runnable r : runnables) {
                virtualExecutor.submit(r);
            }
            virtualExecutor.shutdown();
            while (!virtualExecutor.isTerminated()) {
                Thread.sleep(10);
            }
            Instant end = Instant.now();
            System.out.println("Tempo com virtual threads:        " + Duration.between(start, end));
        }

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveExecutarMuitasTarefasComVirtualThreads() throws Exception {
        //     try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
        //         for (int i = 0; i < 1_000; i++) {
        //             virtualExecutor.submit(VirtualThreadsFundamentos::simulateIoOperation);
        //         }
        //         virtualExecutor.shutdown();
        //         assertTrue(virtualExecutor.awaitTermination(5, TimeUnit.SECONDS));
        //     }
        // }
    }

    private static void simulateIoOperation() {
        try {
            Thread.sleep(10); // simula I/O bloqueante (ex.: chamada HTTP, acesso a disco)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

