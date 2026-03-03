package com.javaai.memory;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * GcFundamentos
 *
 * Objetivo:
 *  - Explicar o garbage collector (GC) de forma prática:
 *    - o que é coletado
 *    - quando objetos se tornam elegíveis
 *    - por que System.gc() é apenas uma sugestão
 *
 * Pontos de especialista:
 *  - GC coleta objetos inacessíveis (sem referências fortes).
 *  - WeakReference permite que o GC colete mesmo com referência "fraca".
 *  - Nunca dependa de System.gc() para lógica de negócio.
 */
public class GcFundamentos {

    static class BigObject {
        private final byte[] data = new byte[1_000_000]; // ~1MB

        @Override
        public String toString() {
            return "BigObject{" + data.length + " bytes}";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Garbage Collector: fundamentos ===");

        // 1) Objeto com referência forte: enquanto houver referência, não será coletado.
        BigObject strong = new BigObject();
        System.out.println("Objeto com referência forte: " + strong);

        // 2) WeakReference: GC pode coletar mesmo com essa referência.
        WeakReference<BigObject> weak = createAndDropStrongReference();
        System.out.println("Antes do GC, weak.get(): " + weak.get());

        System.gc(); // apenas sugestão; pode ou não rodar agora
        Thread.sleep(100);
        System.out.println("Depois do GC, weak.get(): " + weak.get());

        // 3) Vazamento lógico: lista estática ou singleton que nunca libera referências.
        // Aqui apenas ilustramos o problema: manter referência em estrutura global.
        List<BigObject> leak = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            leak.add(new BigObject());
        }
        System.out.println("Simulação de possível 'leak' lógico: lista com " + leak.size() + " objetos grandes.");

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void weakReferencePodeSerColetada() throws InterruptedException {
        //     WeakReference<BigObject> weak = createAndDropStrongReference();
        //     System.gc();
        //     Thread.sleep(100);
        //     // Não há garantia, mas em muitos ambientes weak.get() será null.
        // }
    }

    private static WeakReference<BigObject> createAndDropStrongReference() {
        BigObject temp = new BigObject();
        WeakReference<BigObject> weak = new WeakReference<>(temp);
        // A partir daqui, temp não é mais usado e poderá ser coletado.
        return weak;
    }
}

