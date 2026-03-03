package com.javaai.testing.mockito;

/**
 * SpyExample
 *
 * Objetivo:
 *  - Explicar a diferença entre mock() e spy() no Mockito.
 *
 * Conceito:
 *  - mock(): comportamento default "vazio", tudo é simulado.
 *  - spy(): envolve um objeto real, permitindo sobrescrever apenas partes.
 */
public class SpyExample {

    public static void main(String[] args) {
        System.out.println("=== Mockito – Spy vs Mock ===");
        System.out.println("Use spy() quando quiser manter comportamento real,");
        System.out.println("mas ainda assim verificar interações ou sobrescrever métodos específicos.");
    }

    // ╔══════════════════════════════════════╗
    // ║  COMO TESTAR (JUnit 5 + Mockito)     ║
    // ╚══════════════════════════════════════╝
    //
    // import org.junit.jupiter.api.Test;
    // import java.util.ArrayList;
    // import java.util.List;
    // import static org.mockito.Mockito.*;
    // import static org.junit.jupiter.api.Assertions.*;
    //
    // class SpyExampleTest {
    //
    //     @Test
    //     void deveDiferenciarSpyDeMock() {
    //         List<String> listMock = mock(ArrayList.class);
    //         List<String> listSpy  = spy(new ArrayList<String>());
    //
    //         listMock.add("Clean Code");
    //         listSpy.add("Effective Java");
    //
    //         assertEquals(0, listMock.size()); // mock não executa lógica real
    //         assertEquals(1, listSpy.size());  // spy executa lógica real
    //
    //         verify(listMock).add("Clean Code");
    //         verify(listSpy).add("Effective Java");
    //     }
    // }
}

