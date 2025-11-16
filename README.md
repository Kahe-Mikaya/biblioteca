# Sistema de Cadastro de Livros — Spring Boot

## Sobre o Projeto
Aplicação desenvolvida para gerenciar o catálogo de uma Biblioteca Municipal.  
O sistema permite cadastrar, listar, buscar, atualizar e remover livros.

---

## Arquitetura do Projeto

```

      ├── interface/        # Controllers
      ├── application/      # Casos de uso 
      ├── domain/           # Entidades, regras, portas
      └── infrastructure/   # Repositórios JPA
```

---

## Tecnologias
- Java 17+
- Spring Boot 3.2+
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5 + Mockito

---

## Como Rodar o Projeto

### Rodar o repositório com o Intellij
```bash
git clone https://github.com/Kahe-Mikaya/biblioteca.git
```
#### no Intellij rode a classe BibliotecaApplication.java

### Rodar com Maven
#### caso tenha o maven instalado rode no terminal:

```bash
mvnw spring-boot:run
```

---

## Endpoints da API

### Criar Livro
```
POST /api/livros
```

Body de exemplo:
```json
{
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "isbn": "123456789",
  "anoPublicacao": 1899,
  "quantidadeEstoque": 5
}
```

---

### Listar Todos os Livros
```
GET /api/livros
```

---

### Buscar por ID
```
GET /api/livros/{id}
```

---

### Buscar por ISBN
```
GET /api/livros/by-isbn/{isbn}
```

---

### Atualizar Livro
```
PUT /api/livros/{id}
```

---

### Remover Livro
```
DELETE /api/livros/{id}
```

---

## 🧪 Testes
O projeto possui testes usando:

- JUnit 5  
- Mockito  

---

