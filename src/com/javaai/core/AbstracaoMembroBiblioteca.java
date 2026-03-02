package com.javaai.core;

/**
 * AbstracaoMembroBiblioteca
 *
 * Objetivo:
 *  - Focar no conceito de abstração com classes abstratas.
 *
 * Conceitos abordados:
 *  - Classe abstrata LibraryMember.
 *  - Implementações concretas: StudentMember e ProfessorMember.
 *  - Método abstrato que cada subclasse implementa de forma diferente.
 */
public class AbstracaoMembroBiblioteca {

    static abstract class LibraryMember {
        private final String name;

        LibraryMember(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        /**
         * Cada tipo de membro define sua própria forma de descrever privilégios.
         */
        public abstract String privileges();
    }

    static class StudentMember extends LibraryMember {

        StudentMember(String name) {
            super(name);
        }

        @Override
        public String privileges() {
            return "Estudante %s pode pegar até 5 livros por 15 dias."
                    .formatted(getName());
        }
    }

    static class ProfessorMember extends LibraryMember {

        ProfessorMember(String name) {
            super(name);
        }

        @Override
        public String privileges() {
            return "Professor(a) %s pode pegar até 10 livros por 30 dias."
                    .formatted(getName());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Abstração com membros da biblioteca ===");

        LibraryMember student = new StudentMember("Ana");
        LibraryMember professor = new ProfessorMember("Dr. Carlos");

        System.out.println(student.privileges());
        System.out.println(professor.privileges());

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveDescreverPrivilegiosDoEstudante() {
        //     LibraryMember student = new StudentMember("Ana");
        //     String texto = student.privileges();
        //     assertTrue(texto.contains("5 livros"));
        // }
        //
        // @Test
        // void deveDescreverPrivilegiosDoProfessor() {
        //     LibraryMember professor = new ProfessorMember("Carlos");
        //     String texto = professor.privileges();
        //     assertTrue(texto.contains("10 livros"));
        // }
    }
}

