package com.javaai.memory;

/**
 * MemoryIntegrador
 *
 * Objetivo:
 *  - Programa integrador da seção "memory".
 *  - Combina:
 *    - criação massiva de objetos (heap)
 *    - observação de uso de memória
 *    - elegibilidade para GC
 */
public class MemoryIntegrador {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Memory Integrador ===");

        // 1) Cria muitos objetos (heap) usando lógica similar a JvmMemoriaVisaoGeral.
        JvmMemoriaVisaoGeral.main(args);

        // 2) Demonstra elegibilidade para GC com WeakReference.
        GcFundamentos.main(args);

        // Comentário: na prática, usaríamos ferramentas como:
        //  - jstat, jmap, jcmd
        //  - Java Flight Recorder, Mission Control
        // para análise profunda de memória e GC.

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void integradorDeveExecutarSemErros() {
        //     assertDoesNotThrow(() -> MemoryIntegrador.main(new String[0]));
        // }
    }
}

