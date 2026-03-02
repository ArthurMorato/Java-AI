package com.javaai.core;

/**
 * TiposPrimitivosEVariais
 *
 * Objetivo:
 *  - Explicar, de forma didática, os tipos primitivos de Java,
 *    o tipo de referência (objetos) e as regras básicas de variáveis.
 *  - Mostrar exemplos claros usando o domínio de biblioteca/livros.
 *
 * Conceitos abordados:
 *  - Tipos primitivos: byte, short, int, long, float, double, char, boolean.
 *  - Tipo de referência: String e um tipo de domínio (Book).
 *  - Declaração, inicialização, constantes (final) e inferência com var (Java 10+).
 */
public class TiposPrimitivosEVariais {

    /**
     * Tipo de domínio simples para conectar os exemplos ao contexto de biblioteca.
     */
    static class Book {
        String id;           // tipo de referência (String)
        String title;        // tipo de referência (String)
        int publicationYear; // tipo primitivo (int)

        Book(String id, String title, int publicationYear) {
            this.id = id;
            this.title = title;
            this.publicationYear = publicationYear;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Tipos primitivos e variáveis em Java ===");

        // Inteiros de diferentes tamanhos
        byte quantidadeFiliais = 3;          // -128 a 127
        short quantidadeCidades = 1200;      // -32.768 a 32.767
        int quantidadeLivros = 50_000;       // tipo inteiro mais usado
        long estoqueTotal = 3_000_000_000L;  // note o sufixo 'L' para long

        // Números de ponto flutuante
        float precoMedioLivro = 59.90F;      // sufixo 'F' para float
        double faturamentoAnual = 1_250_000.75; // double é o padrão para decimais

        // Caracter e booleano
        char categoria = 'A';                // um único caractere unicode
        boolean bibliotecaAberta = true;     // verdadeiro ou falso

        // Tipo de referência: String
        String nomeBiblioteca = "Biblioteca Central Java-AI";

        // Tipo de referência: nosso domínio Book
        Book cleanCode = new Book("1", "Clean Code", 2008);

        // Constantes com 'final' (valor não muda após atribuição)
        final double TAXA_MULTA_DIARIA = 2.50;

        // Inferência de tipo com 'var' (Java 10+)
        // O compilador deduz o tipo a partir do valor, mas o tipo continua sendo estático.
        var livrosEmprestadosHoje = 42;           // 'int'
        var mensagemBoasVindas = "Bem-vindo(a)!"; // 'String'

        System.out.println("Nome da biblioteca: " + nomeBiblioteca);
        System.out.println("Quantidade de livros: " + quantidadeLivros);
        System.out.println("Faturamento anual estimado: " + faturamentoAnual);
        System.out.println("Livro de exemplo: " + cleanCode.title + " (" + cleanCode.publicationYear + ")");
        System.out.println("Biblioteca aberta? " + bibliotecaAberta);
        System.out.println("Taxa de multa diária: " + TAXA_MULTA_DIARIA);
        System.out.println("Livros emprestados hoje: " + livrosEmprestadosHoje);
        System.out.println("Mensagem: " + mensagemBoasVindas);

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void deveCriarBookComAnoCorreto() {
        //     Book book = new Book("1", "Clean Code", 2008);
        //     assertEquals(2008, book.publicationYear);
        // }
        //
        // @Test
        // void deveManterConstanteInalterada() {
        //     final double TAXA_MULTA_DIARIA = 2.50;
        //     assertEquals(2.50, TAXA_MULTA_DIARIA);
        // }
    }
}

