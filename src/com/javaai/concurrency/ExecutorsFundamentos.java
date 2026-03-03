package com.javaai.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorsFundamentos
 *
 * Objetivo:
 *  - Mostrar o uso de ExecutorService para gerenciar pools de threads.
 *
 * Pontos de especialista:
 *  - Prefira Executors/ExecutorService a criar Thread na mão.
 *  - Sempre finalize o executor (shutdown / shutdownNow).
 *  - Escolha o tipo de pool de acordo com a carga (CPU-bound vs IO-bound).
 *
 * Domínio: processamento de relatórios da biblioteca em paralelo.
 */
public class ExecutorsFundamentos {

    static class ReportTask implements Callable<String> {
        private final String libraryBranch;

        ReportTask(String libraryBranch) {
            this.libraryBranch = libraryBranch;
        }

        @Override
        public String call() throws Exception {
            // Simula trabalho pesado de I/O (consulta DB, leitura de arquivos etc.)
            Thread.sleep(200);
            return "Relatório da filial %s gerado por %s"
                    .formatted(libraryBranch, Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("=== ExecutorService: gerenciamento de pools de threads ===");

        // newFixedThreadPool: bom para workloads previsíveis.
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = List.of(
                new ReportTask("Centro"),
                new ReportTask("Zona Norte"),
                new ReportTask("Zona Sul"),
                new ReportTask("Zona Leste"),
                new ReportTask("Zona Oeste")
        );

        List<Future<String>> futures = new ArrayList<>();
        for (Callable<String> task : tasks) {
            futures.add(executor.submit(task));
        }

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        // Sempre finalize o executor para liberar recursos.
        executor.shutdown();

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveExecutarTarefasEmPoolDeThreads() throws Exception {
        //     ExecutorService executor = Executors.newFixedThreadPool(2);
        //     Future<String> f1 = executor.submit(new ReportTask("Centro"));
        //     Future<String> f2 = executor.submit(new ReportTask("Zona Norte"));
        //     assertNotNull(f1.get());
        //     assertNotNull(f2.get());
        //     executor.shutdown();
        // }
    }
}

