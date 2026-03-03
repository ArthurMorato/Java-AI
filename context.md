# Contexto do projeto

## Projeto
- **Nome**: Java-AI
- **Descrição**: Projeto de estudo Java 21+ com foco em cobrir todos os temas necessários para se tornar um Java Expert. Cada classe é autocontida, comentada e didática.
- **Stack**: Java Core 21 + NoSQL (MongoDB, somente se necessário)
- **Status**: Módulo `testing/` concluído — próximo módulo a definir

---

## Decisões arquiteturais
- **Arquitetura**: Modular Monolith por pacotes temáticos
  - **Critério**: didático, cada pacote representa um pilar do conhecimento Java
- **Domínio**: Biblioteca/Livros (Book, Author, Library, Member)
  - **Critério**: simples, intuitivo e reutilizável em todos os módulos
- **Banco**: MongoDB
  - **Critério**: NoSQL mais adotado no mercado, boa integração com Java
- **Regra**: sempre justificar a escolha quando houver duas ou mais opções tecnológicas

---

## Estrutura de pacotes
src/com.javaai/
  ├── core/        → oop, collections, generics, exceptions, io
  ├── modern/      → streams, optional, functional, records, sealed, patterns
  ├── concurrency/ → threads, executors, concurrent, locks, virtual
  ├── memory/      → gc, jvm
  ├── design/      → solid, creational, structural, behavioral
  ├── database/    → nosql
  └── testing/
      ├── junit5/       → BasicAssertionsExample, LifecycleExample,
      │                   ExceptionsTestExample, ParameterizedExample,
      │                   AssumptionsExample
      ├── mockito/      → BasicMockExample, SpyExample,
      │                   ArgumentCaptorExample, InjectMocksExample
      ├── patterns/     → AAAPatternExample, GivenWhenThenExample,
      │                   TestDataBuilderExample
      └── integration/  → TestingIntegration

---

## Entidades / modelos
- **Book**: (id, title, author, year, genre, price)
- **Author**: (id, name, nationality, birthYear)
- **Library**: (id, name, books)
- **Member**: (id, name, email, borrowedBooks)

---

## Ordem de implementação
- **Sequência**: core → modern → concurrency → memory → design → database
- **Regra**: implementar **um** módulo por vez
- **Antes de iniciar**: perguntar qual módulo implementar
- **Disciplina**: seguir estritamente as regras sem desvios
- **Nota**: `testing/` foi implementado fora da sequência principal por decisão estratégica
  — serve de referência para todos os outros módulos

---

## Convenções de código
- Toda classe de assunto **único** é um programa standalone com método `main()`, autocontida e executável
- Classes **não** precisam se comunicar entre si (sem acoplamento obrigatório)
- Cada classe exemplifica **um** conceito específico com clareza
- Ao final de cada seção (subpacote), criar um programa integrador que combine **todos**
  os conceitos da seção em um único programa como exemplo de tarefa real
- Comentários detalhados explicando o conceito abordado
- Bloco de comentário ao final de cada classe exemplificando testes unitários com JUnit 5,
  mesmo sem arquivo de teste real:
```
// ╔══════════════════════════════════════╗
// ║  COMO TESTAR (JUnit 5)               ║
// ╚══════════════════════════════════════╝
// @Test void deve...() { ... }
```

---

## Convenções do módulo `testing/`
- Único módulo onde cada conceito possui **dois arquivos**:
  - `NomeExample.java` → standalone com `main()` explicativo
  - `NomeExampleTest.java` → arquivo de teste real e executável (JUnit 5 + Mockito)
- Dependências necessárias (`pom.xml`):
  - `org.junit.jupiter:junit-jupiter:5.10.0` (scope: test)
  - `org.mockito:mockito-core:5.5.0` (scope: test)

---

## Segurança / variáveis de ambiente
- Credenciais do MongoDB armazenadas em arquivo `.env` (nunca commitado)
- `.env.example` com valores fictícios sobe para o repositório como referência
- `.gitignore` bloqueia `.env` e `*.env` (exceto `.env.example`)
- Leitura no Java via `System.getenv()` ou lib `dotenv-java`

---

## Regras gerais
- Usar apenas Java 21 + MongoDB (somente se necessário)
- Foco didático acima de tudo: clareza > elegância
- Quando houver dúvida sobre qual decisão tomar, perguntar antes de implementar
- Sempre explicar o critério de escolha quando existir mais de uma opção

---

## Funcionalidades
- ✅ Concluído: Estrutura, arquitetura e convenções definidas
- ✅ Concluído: Módulo `testing/` (junit5, mockito, patterns, integration)
- 🔄 Em progresso: Aguardando início do próximo módulo
- ⏳ Pendente: core, modern, concurrency, memory, design, database

---

## Últimas alterações
- Módulo `testing/` implementado com 12 classes em 4 subpacotes
- Adicionada seção de convenções exclusivas do módulo `testing/`
- Adicionada seção de segurança / variáveis de ambiente
- Status atualizado para refletir o módulo concluído

---

## Dúvidas / pontos em aberto
- (vazio — preencher conforme surgirem decisões pendentes reais)
