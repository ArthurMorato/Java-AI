# Contexto do projeto

## Projeto

- **Nome**: Java-AI
- **Descrição**: Projeto de estudo Java 21+ com foco em cobrir todos os temas necessários para se tornar um Java Expert. Cada classe é autocontida, comentada e didática.
- **Stack**: Java Core 21 + NoSQL (MongoDB, somente se necessário)
- **Status**: Estrutura definida — aguardando geração de código via Cursor

## Decisões arquiteturais

- **Arquitetura**: Modular Monolith por pacotes temáticos  
  - **Critério**: didático, cada pacote representa um pilar do conhecimento Java
- **Domínio**: Biblioteca/Livros (Book, Author, Library, Member)  
  - **Critério**: simples, intuitivo e reutilizável em todos os módulos
- **Banco**: MongoDB  
  - **Critério**: NoSQL mais adotado no mercado, boa integração com Java
- **Regra**: sempre justificar a escolha quando houver duas ou mais opções tecnológicas

## Estrutura de pacotes

```
src/com.javaai/
  ├── core/        → oop, collections, generics, exceptions, io
  ├── modern/      → streams, optional, functional, records, sealed, patterns
  ├── concurrency/ → threads, executors, concurrent, locks, virtual
  ├── memory/      → gc, jvm
  ├── design/      → solid, creational, structural, behavioral
  └── database/    → nosql
```

## Entidades / modelos

- **Book**: (id, title, author, year, genre, price)
- **Author**: (id, name, nationality, birthYear)
- **Library**: (id, name, books)
- **Member**: (id, name, email, borrowedBooks)

## Ordem de implementação

- **Sequência**: core → modern → concurrency → memory → design → database
- **Regra**: implementar **um** módulo por vez
- **Antes de iniciar**: perguntar qual módulo implementar
- **Disciplina**: seguir estritamente as regras sem desvios

## Convenções de código

- Toda classe de assunto **ÚNICO** é um programa standalone com método `main()`, autocontida e executável
- Classes **não** precisam se comunicar entre si (sem acoplamento obrigatório)
- Cada classe exemplifica **um** conceito específico com clareza
- Ao final de cada seção (subpacote), criar um programa integradora que combine **todos** os conceitos da seção em que pertence em um único programa como exemplo de tarefa que utilize as habilidades da seção.
- Comentários detalhados explicando o conceito abordado
- Bloco de comentário ao final de cada classe exemplificando testes unitários com JUnit 5, mesmo sem arquivo de teste real:

```
// ╔══════════════════════════════════════╗
// ║  COMO TESTAR (JUnit 5)               ║
// ╚══════════════════════════════════════╝
// @Test void deve...() { ... }
```

## Regras gerais

- Usar apenas Java 21 + MongoDB (somente se necessário)
- Foco didático acima de tudo: clareza > elegância
- Quando houver dúvida sobre qual decisão tomar, perguntar antes de implementar
- Sempre explicar o critério de escolha quando existir mais de uma opção

## Funcionalidades

- ✅ Concluído: Estrutura, arquitetura e convenções definidas
- 🔄 Em progresso: Geração de código via Cursor
- ⏳ Pendente: Implementação dos módulos (um por vez)

## Últimas alterações

- Estrutura de pacotes definida
- Entidades base do domínio Biblioteca definidas
- Convenções de código e testes estabelecidas
- Ordem de implementação definida

## Dúvidas / pontos em aberto

- (vazio — preencher conforme surgirem decisões pendentes reais)

