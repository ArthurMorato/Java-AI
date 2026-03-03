package com.javaai.modern;

import java.util.Objects;

/**
 * SealedEPatternMatching
 *
 * Objetivo:
 *  - Ensinar sealed classes/interfaces (Java 17+) e pattern matching em Java 21.
 *  - Mostrar como isso melhora:
 *    - modelagem de domínio
 *    - exaustividade (especialmente em switch)
 *    - legibilidade e segurança
 *
 * Pontos de especialista:
 *  - Sealed "fecha" a hierarquia: só tipos permitidos podem estender/implementar.
 *  - Isso permite raciocínio exaustivo: o compilador sabe todas as variações possíveis.
 *  - Pattern matching no switch (Java 21) é poderoso para ADTs (algebraic data types) em Java.
 *
 * Domínio: biblioteca (membro com tipos fechados).
 */
public class SealedEPatternMatching {

    /**
     * Hierarquia fechada: apenas Student, Professor e Guest podem ser Member.
     */
    sealed interface Member permits Student, Professor, Guest {
        String name();
    }

    record Student(String name, int currentSemester) implements Member {
        Student {
            Objects.requireNonNull(name);
            if (currentSemester <= 0) throw new IllegalArgumentException("semester inválido");
        }
    }

    record Professor(String name, String department) implements Member {
        Professor {
            Objects.requireNonNull(name);
            Objects.requireNonNull(department);
        }
    }

    record Guest(String name) implements Member {
        Guest {
            Objects.requireNonNull(name);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Sealed + pattern matching (Java 21) ===");

        Member a = new Student("Ana", 3);
        Member b = new Professor("Dr. Carlos", "Computação");
        Member c = new Guest("Visitante");

        System.out.println(descreverPrivilegios(a));
        System.out.println(descreverPrivilegios(b));
        System.out.println(descreverPrivilegios(c));

        // Pattern matching com instanceof (também útil fora de switch)
        Object maybeMember = a;
        if (maybeMember instanceof Student s) { // s já vem tipado
            System.out.println("\ninstanceof pattern: " + s.name() + " está no semestre " + s.currentSemester());
        }

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveDescreverPrivilegiosDeStudent() {
        //     Member m = new Student("Ana", 3);
        //     assertTrue(descreverPrivilegios(m).contains("5 livros"));
        // }
        //
        // @Test
        // void deveLancarErroParaSemestreInvalido() {
        //     assertThrows(IllegalArgumentException.class,
        //         () -> new Student("Ana", 0));
        // }
    }

    /**
     * switch com padrões (Java 21):
     *  - Cada case faz "desestruturação" do tipo via pattern matching.
     *  - Como Member é sealed, o switch pode ser exaustivo.
     */
    static String descreverPrivilegios(Member member) {
        return switch (member) {
            case Student s -> "Estudante %s (semestre %d) pode pegar até 5 livros por 15 dias."
                    .formatted(s.name(), s.currentSemester());
            case Professor p -> "Professor(a) %s (%s) pode pegar até 10 livros por 30 dias."
                    .formatted(p.name(), p.department());
            case Guest g -> "Convidado %s pode consultar livros no local (sem empréstimo)."
                    .formatted(g.name());
        };
    }
}

