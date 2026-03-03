package com.javaai.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LocksFundamentos
 *
 * Objetivo:
 *  - Mostrar como sincronizar acesso a estado compartilhado usando:
 *    - synchronized
 *    - ReentrantLock
 *
 * Pontos de especialista:
 *  - synchronized é mais simples, mas menos flexível.
 *  - ReentrantLock oferece recursos avançados (tryLock, fairness etc.).
 *  - Sempre garanta unlock em bloco finally.
 *
 * Domínio: controle de estoque de livros.
 */
public class LocksFundamentos {

    static class Inventory {
        private int stock = 0;
        private final Lock lock = new ReentrantLock();

        // Versão usando synchronized
        public synchronized void addWithSynchronized(int quantity) {
            stock += quantity;
        }

        // Versão usando ReentrantLock
        public void addWithLock(int quantity) {
            lock.lock();
            try {
                stock += quantity;
            } finally {
                lock.unlock();
            }
        }

        public synchronized int getStock() {
            return stock;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Locks: synchronized vs ReentrantLock ===");

        Inventory inventory = new Inventory();

        Runnable taskSync = () -> {
            for (int i = 0; i < 10_000; i++) {
                inventory.addWithSynchronized(1);
            }
        };

        Thread t1 = new Thread(taskSync);
        Thread t2 = new Thread(taskSync);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Estoque esperado (synchronized): 20000, real: " + inventory.getStock());

        // Reinicia o estoque
        Inventory inventoryLock = new Inventory();
        Runnable taskLock = () -> {
            for (int i = 0; i < 10_000; i++) {
                inventoryLock.addWithLock(1);
            }
        };

        Thread t3 = new Thread(taskLock);
        Thread t4 = new Thread(taskLock);
        t3.start();
        t4.start();
        t3.join();
        t4.join();

        System.out.println("Estoque esperado (ReentrantLock): 20000, real: " + inventoryLock.getStock());

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveControlarEstoqueComSynchronized() throws InterruptedException {
        //     Inventory inventory = new Inventory();
        //     Runnable task = () -> {
        //         for (int i = 0; i < 10_000; i++) inventory.addWithSynchronized(1);
        //     };
        //     Thread t1 = new Thread(task);
        //     Thread t2 = new Thread(task);
        //     t1.start(); t2.start();
        //     t1.join(); t2.join();
        //     assertEquals(20_000, inventory.getStock());
        // }
    }
}

