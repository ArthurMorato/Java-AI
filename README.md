# Java-AI

[![Java](https://img.shields.io/badge/Java-21+-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-6+-47A248?style=flat-square&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](https://opensource.org/licenses/MIT)

> Projeto de estudo **Java 21+** com foco em cobrir todos os temas necessários para se tornar um **Java Expert**. Cada classe é autocontida, comentada e didática.

---

## Índice

- [Sobre o projeto](#-sobre-o-projeto)
- [Objetivos](#-objetivos)
- [Stack e requisitos](#-stack-e-requisitos)
- [Arquitetura](#-arquitetura)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Domínio: Biblioteca](#-domínio-biblioteca)
- [Convenções de código](#-convenções-de-código)
- [Ordem de implementação](#-ordem-de-implementação)
- [Como executar](#-como-executar)
- [Status e roadmap](#-status-e-roadmap)
- [Contribuição](#-contribuição)
- [Licença](#-licença)

---

## Sobre o projeto

O **Java-AI** é um repositório de estudo estruturado para quem deseja dominar Java moderno (21+) de forma progressiva e organizada. Em vez de exemplos soltos, o projeto adota uma **arquitetura modular por pacotes temáticos**, onde cada pacote representa um pilar do conhecimento Java, aplicado a um domínio único e intuitivo: **Biblioteca/Livros**.

Cada classe é pensada para ser **autocontida** (com `main()` executável), **comentada** e **didática**, permitindo estudo isolado ou em sequência. O código prioriza **clareza sobre elegância**, com decisões técnicas sempre justificadas quando houver mais de uma opção.

---

## Objetivos

- Cobrir os temas essenciais para se tornar um **Java Expert**.
- Manter exemplos **prontos para executar** e fáceis de entender.
- Aplicar um **domínio único** (Biblioteca) em todos os módulos para facilitar a conexão entre conceitos.
- Documentar **decisões arquiteturais** e convenções para consistência e manutenção.
- Servir como material de referência e portfólio de conhecimento em Java 21+.

---

## Stack e requisitos

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| **Java** | 21+ | Linguagem e runtime |
| **MongoDB** | 6+ (quando necessário) | Persistência NoSQL |

**Por que Java 21+?**  
Recursos como *Virtual Threads*, *Pattern Matching*, *Records*, *Sealed Classes* e APIs modernas de concorrência e I/O fazem parte do ecossistema atual e são esperados em projetos profissionais.

**Por que MongoDB?**  
É o NoSQL mais adotado no mercado, com ótima integração com o ecossistema Java (drivers oficiais, Spring Data, etc.) e adequado para o domínio de documentos (livros, autores, bibliotecas).

**Requisitos de ambiente:**

- JDK 21 ou superior (`java -version`)
- Maven ou Gradle (conforme configurado no projeto)
- MongoDB instalado e em execução apenas para o módulo `database`

---

## Arquitetura

O projeto segue uma **Arquitetura Modular Monolith por pacotes temáticos**: um único repositório e uma única aplicação, mas com fronteiras claras por tema. Cada pacote under `com.javaai` representa um pilar do conhecimento Java.

**Critérios da escolha:**

- **Didático**: cada pacote = um conjunto de conceitos coesos.
- **Simples de navegar**: não há microserviços; o foco é no aprendizado da linguagem e do ecossistema.
- **Escalável em conteúdo**: novos tópicos podem ser adicionados como novos subpacotes ou classes sem quebrar o que já existe.

Não há obrigação de comunicação entre classes de pacotes diferentes; quando houver integração, ela será feita de forma explícita (por exemplo, classes integradoras ao final de cada seção).

---

## Estrutura do projeto

```
src/com/javaai/
├── core/           # OOP, Collections, Generics, Exceptions, I/O
├── modern/         # Streams, Optional, Functional, Records, Sealed, Patterns
├── concurrency/    # Threads, Executors, Concurrent API, Locks, Virtual Threads
├── memory/         # GC, JVM
├── design/         # SOLID, Creational, Structural, Behavioral
└── database/       # NoSQL (MongoDB)
```

Cada pacote contém subpacotes ou classes que exemplificam **um conceito por vez**. Ao final de cada seção (subpacote), há classe(s) **integradora(s)** que combinam todos os conceitos daquela seção em um único programa demonstrativo.

---

## Domínio: Biblioteca

Para manter coerência em todos os módulos, o domínio é único:

| Entidade | Campos principais |
|----------|-------------------|
| **Book** | id, title, author, year, genre, price |
| **Author** | id, name, nationality, birthYear |
| **Library** | id, name, books |
| **Member** | id, name, email, borrowedBooks |

Esse domínio é **simples**, **intuitivo** e **reutilizável** em exemplos de OOP, collections, streams, concorrência, persistência e padrões de projeto, sem necessidade de novos modelos a cada módulo.

---

## Convenções de código

- **Uma classe = um conceito**: cada classe exemplifica um tópico específico com clareza.
- **Standalone**: toda classe possui `main()` e é executável de forma isolada.
- **Sem acoplamento obrigatório**: classes não precisam se comunicar entre si; quando houver integração, será em classes integradoras dedicadas.
- **Comentários didáticos**: explicação do conceito abordado diretamente no código.
- **Bloco de testes (JUnit 5)**: ao final de cada classe, um bloco de comentário descreve como testar com JUnit 5, mesmo sem arquivo de teste real, no formato:

  ```
  // ╔══════════════════════════════════════╗
  // ║  COMO TESTAR (JUnit 5)               ║
  // ╚══════════════════════════════════════╝
  // @Test void deve...() { ... }
  ```

- **Clareza > elegância**: em caso de dúvida, optar pela solução mais legível e didática.

---

## Ordem de implementação

Os módulos devem ser implementados **um por vez**, na seguinte ordem:

1. **core** — fundamentos (OOP, collections, generics, exceptions, I/O)
2. **modern** — recursos modernos (streams, optional, functional, records, sealed, patterns)
3. **concurrency** — threads, executors, concurrent API, locks, virtual threads
4. **memory** — GC e JVM
5. **design** — SOLID e padrões (creational, structural, behavioral)
6. **database** — NoSQL com MongoDB

Antes de iniciar um novo módulo, a decisão de *qual* módulo implementar deve ser explícita. Não há desvios em relação a essa ordem e às regras definidas no contexto do projeto.

---

## Como executar

### Pré-requisitos

- JDK 21+ instalado.
- (Opcional) MongoDB em execução para o módulo `database`.

### Compilação e execução

```bash
# Clone o repositório
git clone https://github.com/<seu-usuario>/Java-AI.git
cd Java-AI

# Compilar (exemplo com Maven)
mvn clean compile

# Executar uma classe específica (exemplo)
mvn exec:java -Dexec.mainClass="com.javaai.core.oop.ExemploOOP"
```

Ou, com o JDK diretamente:

```bash
javac --release 21 -d out src/com/javaai/<pacote>/<Classe>.java
java -cp out com.javaai.<pacote>.<Classe>
```

Substitua `<pacote>` e `<Classe>` pelo caminho da classe que deseja executar.

---

## Status e roadmap

| Item | Status |
|------|--------|
| Estrutura de pacotes | Concluído |
| Arquitetura e convenções | Concluído |
| Entidades do domínio | Definidas |
| Geração de código (Cursor) | Em progresso |
| Implementação dos módulos | Pendente (um por vez) |

**Próximos passos:** implementar o módulo **core** e, em seguida, seguir a ordem definida (modern → concurrency → memory → design → database).

---

## Contribuição

Contribuições são bem-vindas. Ao propor mudanças:

- Respeite a ordem de implementação e as convenções de código.
- Mantenha cada classe autocontida e didática.
- Documente decisões quando houver mais de uma opção técnica.

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (`git checkout -b feature/nome-da-feature`).
3. Commit com mensagens claras (`git commit -m 'Adiciona exemplo de X no core'`).
4. Push para a branch e abra um Pull Request.

---

## Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## Autor

**Arthur Morato** — 2026

Se este projeto te ajudou no caminho para se tornar um Java Expert, considere dar uma ⭐ no repositório.
